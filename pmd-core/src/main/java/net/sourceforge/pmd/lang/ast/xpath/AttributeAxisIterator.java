/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ast.xpath;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.sourceforge.pmd.lang.ast.Node;


/**
 * Explores an AST node reflectively to iterate over its XPath
 * attributes. This is the default way the attributes of a node
 * are made accessible to XPath rules, and defines an important
 * piece of PMD's XPath support.
 */
public class AttributeAxisIterator implements Iterator<Attribute> {

    /**
     * Associates an attribute accessor with the XPath-accessible
     * name of the attribute. This is used to avoid recomputing
     * the name of the attribute for each attribute (it's only done
     * once and put inside the {@link #METHOD_CACHE}.
     */
    private static class MethodWrapper {
        public Method method;
        public String name;


        MethodWrapper(Method m) {
            this.method = m;
            this.name = truncateMethodName(m.getName());
        }


        /**
         * This method produces the actual XPath name of an attribute
         * from the name of its accessor.
         */
        private String truncateMethodName(String n) {
            // about 70% of the methods start with 'get', so this case goes
            // first
            if (n.startsWith("get")) {
                return n.substring("get".length());
            }
            if (n.startsWith("is")) {
                return n.substring("is".length());
            }
            if (n.startsWith("has")) {
                return n.substring("has".length());
            }
            if (n.startsWith("uses")) {
                return n.substring("uses".length());
            }

            return n;
        }
    }

    private Attribute currObj;
    private MethodWrapper[] methodWrappers;
    private int position;
    private Node node;

    private static final ConcurrentMap<Class<?>, MethodWrapper[]> METHOD_CACHE = new ConcurrentHashMap<>();


    /**
     * Creates a new iterator that enumerates the attributes of the given node.
     */
    public AttributeAxisIterator(Node contextNode) {
        this.node = contextNode;
        if (!METHOD_CACHE.containsKey(contextNode.getClass())) {
            Method[] preFilter = contextNode.getClass().getMethods();
            List<MethodWrapper> postFilter = new ArrayList<>();
            for (Method element : preFilter) {
                if (isAttributeAccessor(element)) {
                    postFilter.add(new MethodWrapper(element));
                }
            }
            METHOD_CACHE.putIfAbsent(contextNode.getClass(), postFilter.toArray(new MethodWrapper[0]));
        }
        this.methodWrappers = METHOD_CACHE.get(contextNode.getClass());

        this.position = 0;
        this.currObj = getNextAttribute();
    }


    @Override
    public Attribute next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException();
        }
        Attribute ret = currObj;
        currObj = getNextAttribute();
        return ret;
    }


    @Override
    public boolean hasNext() {
        return currObj != null;
    }


    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }


    private Attribute getNextAttribute() {
        if (methodWrappers == null || position == methodWrappers.length) {
            return null;
        }
        MethodWrapper m = methodWrappers[position++];
        return new Attribute(node, m.name, m.method);
    }


    private static final Set<Class<?>> CONSIDERED_RETURN_TYPES
            = new HashSet<>(Arrays.<Class<?>>asList(Integer.TYPE, Boolean.TYPE, Double.TYPE, String.class, Long.TYPE, Character.TYPE, Float.TYPE));

    private static final Set<String> FILTERED_OUT_NAMES
            = new HashSet<>(Arrays.asList("toString", "getClass", "getXPathNodeName", "getTypeNameNode", "hashCode", "getImportedNameNode", "getScope"));


    /**
     * Returns whether the given method is an attribute accessor,
     * in which case a corresponding Attribute will be added to
     * the iterator.
     *
     * @param method The method to test
     */
    protected boolean isAttributeAccessor(Method method) {
        String methodName = method.getName();

        return CONSIDERED_RETURN_TYPES.contains(method.getReturnType())
                && method.getParameterTypes().length == 0
                && !methodName.startsWith("jjt")
                && !FILTERED_OUT_NAMES.contains(methodName);
    }
}
