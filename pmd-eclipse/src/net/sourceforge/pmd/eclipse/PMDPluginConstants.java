package net.sourceforge.pmd.eclipse;

import org.eclipse.core.runtime.QualifiedName;

/**
 * This interface groups all plugin constants
 * 
 * @author Philippe Herlin
 * @version $Revision$
 * 
 * $Log$
 * Revision 1.1  2004/06/29 22:00:30  phherlin
 * Adapting the plugin to the new OSGi standards
 *
 */
public interface PMDPluginConstants {
    public static final String PLUGIN_ID = "net.sourceforge.pmd.eclipse";

    public static final String[] RULESET_ALLPMD = {"rulesets/basic.xml", "rulesets/braces.xml", "rulesets/codesize.xml",
            "rulesets/controversial.xml", "rulesets/coupling.xml", "rulesets/design.xml", "rulesets/finalizers.xml",
            "rulesets/imports.xml", "rulesets/javabeans.xml", "rulesets/junit.xml", "rulesets/naming.xml",
            "rulesets/scratchpad.xml", "rulesets/strictexception.xml", "rulesets/strings.xml", "rulesets/unusedcode.xml"};

    public static final String[] RULESET_DEFAULTLIST = RULESET_ALLPMD;
    public static final String RULESET_PREFERENCE = PLUGIN_ID + ".ruleset";
    public static final String RULESET_DEFAULT = "";
    public static final String RULESET_FILE = "/ruleset.xml";

    public static final String MIN_TILE_SIZE_PREFERENCE = PLUGIN_ID + ".CPDPreference.mintilesize";
    public static final int MIN_TILE_SIZE_DEFAULT = 25;

    public static final String PMD_MARKER = PLUGIN_ID + ".pmdMarker";
    public static final String PMD_TASKMARKER = PLUGIN_ID + ".pmdTaskMarker";

    public static final QualifiedName SESSION_PROPERTY_ACTIVE_RULESET = new QualifiedName(PLUGIN_ID + ".sessprops", "active_rulset");
    public static final QualifiedName PERSISTENT_PROPERTY_ACTIVE_RULESET = new QualifiedName(PLUGIN_ID + ".persprops", "active_rulset");
    public static final QualifiedName SESSION_PROPERTY_WORKINGSET = new QualifiedName(PLUGIN_ID + ".sessprops", "workingset");
    public static final QualifiedName PERSISTENT_PROPERTY_WORKINGSET = new QualifiedName(PLUGIN_ID + ".persprops", "workingset");
    public static final QualifiedName SESSION_PROPERTY_STORE_RULESET_PROJECT = new QualifiedName(PLUGIN_ID + ".sessprops", "store_ruleset_project");
    public static final QualifiedName PERSISTENT_PROPERTY_STORE_RULESET_PROJECT = new QualifiedName(PLUGIN_ID + ".persprops", "store_ruleset_project");
    public static final QualifiedName SESSION_PROPERTY_RULESET_MODIFICATION_STAMP = new QualifiedName(PLUGIN_ID + ".sessprops", "ruleset_modification_stamp");

    public static final String LIST_DELIMITER = ";";

    public static final String ICON_ERROR = "icons/error.gif";
    public static final String ICON_WARN = "icons/warn.gif";
    public static final String ICON_INFO = "icons/info.gif";
    public static final String ICON_PROJECT = "icons/prj.gif";
    public static final String ICON_FILE = "icons/file.gif";
    public static final String ICON_PRIO1 = "icons/prio_1.gif";
    public static final String ICON_PRIO2 = "icons/prio_2.gif";
    public static final String ICON_PRIO3 = "icons/prio_3.gif";
    public static final String ICON_PRIO4 = "icons/prio_4.gif";
    public static final String ICON_PRIO5 = "icons/prio_5.gif";
    public static final String ICON_REMVIO = "icons/remvio.gif";

    public static final String KEY_MARKERATT_RULENAME = "rulename";

    public static final String SETTINGS_VIEW_FILE_SELECTION = "view.file_selection";
    public static final String SETTINGS_VIEW_PROJECT_SELECTION = "view.project_selection";
    public static final String SETTINGS_VIEW_ERRORHIGH_FILTER = "view.errorhigh_filter";
    public static final String SETTINGS_VIEW_ERROR_FILTER = "view.high_filter";
    public static final String SETTINGS_VIEW_WARNINGHIGH_FILTER = "view.warninghigh_filter";
    public static final String SETTINGS_VIEW_WARNING_FILTER = "view.warning_filter";
    public static final String SETTINGS_VIEW_INFORMATION_FILTER = "view.information_filter";

    public static final String REVIEW_MARKER = "// @PMD:REVIEWED:";
    public static final String REVIEW_ADDITIONAL_COMMENT_DEFAULT = "by {0} on {1}";
    public static final String REVIEW_ADDITIONAL_COMMENT_PREFERENCE = PLUGIN_ID + ".review_additional_comment";

}