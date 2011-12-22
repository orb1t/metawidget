package org.metawidget.statically.spring.widgetprocessor;

import static org.metawidget.inspector.InspectionResultConstants.*;

import java.util.Map;

import org.metawidget.statically.spring.StaticSpringMetawidget;
import org.metawidget.statically.spring.widgetbuilder.FormInputTag;
import org.metawidget.util.CollectionUtils;

import junit.framework.TestCase;

/**
 * JUnit test for the Hidden Field Widget Processor
 * 
 * @author Ryan Bradley
 */

public class HiddenFieldProcessorTest
    extends TestCase {
    
    //
    // Public methods
    //
    
    public void testWidgetProcessor() {
        HiddenFieldProcessor processor = new HiddenFieldProcessor();
        FormInputTag springInput = new FormInputTag();
        StaticSpringMetawidget metawidget = new StaticSpringMetawidget();        
        Map<String, String> attributes = CollectionUtils.newHashMap();
        
        // Not hidden
        
        attributes.put( HiddenFieldProcessor.ATTRIBUTE_NEEDS_HIDDEN_FIELD, FALSE );
        processor.processWidget( springInput, PROPERTY, attributes, metawidget );
        assertEquals( "<form:input/>", springInput.toString() );
        
        // Null value
        
        attributes.put( NAME, "foo" );
        attributes.put( HiddenFieldProcessor.ATTRIBUTE_NEEDS_HIDDEN_FIELD, TRUE );
        processor.processWidget( springInput, PROPERTY, attributes, metawidget );
        assertEquals( "<form:input><form:hidden path=\"foo\"/></form:input>", springInput.toString() );
        
        // Simple value (i.e. no '.' characters used as separators)
        
        springInput = new FormInputTag();
        attributes.put( NAME, "foo" );
        metawidget.setValue( "bar" );
        processor.processWidget( springInput, PROPERTY, attributes, metawidget );
        assertEquals( "<form:input><form:hidden path=\"foo\"/></form:input>", springInput.toString() );
        
        // Complex metawidget value (i.e. using '.' separators)
        
        springInput = new FormInputTag();
        attributes.put( NAME, "spring" );
        metawidget.setValue( "test.org.metawidget.statically" );
        processor.processWidget( springInput, PROPERTY, attributes, metawidget );
        assertEquals( "<form:input><form:hidden path=\"org.metawidget.statically.spring\"/></form:input>", springInput.toString() );
    }
    
}
