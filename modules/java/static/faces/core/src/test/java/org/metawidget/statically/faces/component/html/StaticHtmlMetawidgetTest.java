// Metawidget
//
// This file is dual licensed under both the LGPL
// (http://www.gnu.org/licenses/lgpl-2.1.html) and the EPL
// (http://www.eclipse.org/org/documents/epl-v10.php). As a
// recipient of Metawidget, you may choose to receive it under either
// the LGPL or the EPL.
//
// Commercial licenses are also available. See http://metawidget.org
// for details.

package org.metawidget.statically.faces.component.html;

import java.io.StringWriter;
import java.util.Map;

import junit.framework.TestCase;

import org.metawidget.inspector.annotation.UiSection;
import org.metawidget.statically.faces.component.html.layout.HtmlPanelGrid;

/**
 * @author <a href="http://kennardconsulting.com">Richard Kennard</a>
 */

public class StaticHtmlMetawidgetTest
	extends TestCase {

	//
	// Public methods
	//

	public static void testNoNamespace() {

		assertEquals( null, new StaticHtmlMetawidget().getNamespaceURI() );
	}

	public void testNullPath() {

		StaticHtmlMetawidget metawidget = new StaticHtmlMetawidget();
		assertEquals( "<h:panelGrid columns=\"3\"/>", metawidget.toString() );

		Map<String, String> namespaces = metawidget.getNamespaces();
		assertEquals( "http://java.sun.com/jsf/html", namespaces.get( "h" ) );
		assertEquals( 1, namespaces.size() );
	}

	public void testAdditionalNamespaces() {

		HtmlPanelGrid panelGrid = new HtmlPanelGrid();
		panelGrid.putAdditionalNamespaceURI( "foo", "http://foo.bar" );
		StaticHtmlMetawidget metawidget = new StaticHtmlMetawidget();
		metawidget.getChildren().add( panelGrid );

		Map<String, String> namespaces = metawidget.getNamespaces();
		assertEquals( "http://java.sun.com/jsf/html", namespaces.get( "h" ) );
		assertEquals( "http://foo.bar", namespaces.get( "foo" ) );
		assertEquals( 2, namespaces.size() );
	}

	public void testInitialIndent() {

		StaticHtmlMetawidget metawidget = new StaticHtmlMetawidget();
		metawidget.setPath( Foo.class.getName() );
		StringWriter writer = new StringWriter();
		metawidget.write( writer, 0 );

		String result = "<h:panelGrid columns=\"3\">\r\n" +
				"\t<h:outputLabel value=\"Bar:\"/>\r\n" +
				"\t<h:panelGroup>\r\n" +
				"\t\t<h:inputText/>\r\n" +
				"\t\t<h:message/>\r\n" +
				"\t</h:panelGroup>\r\n" +
				"\t<h:outputText/>\r\n" +
				"\t<h:outputLabel value=\"Baz:\"/>\r\n" +
				"\t<h:panelGroup>\r\n" +
				"\t\t<h:inputText/>\r\n" +
				"\t\t<h:message/>\r\n" +
				"\t</h:panelGroup>\r\n" +
				"\t<h:outputText/>\r\n" +
				"</h:panelGrid>\r\n";
		assertEquals( result, writer.toString() );

		writer = new StringWriter();
		metawidget.write( writer, 1 );

		result = "\t<h:panelGrid columns=\"3\">\r\n" +
				"\t\t<h:outputLabel value=\"Bar:\"/>\r\n" +
				"\t\t<h:panelGroup>\r\n" +
				"\t\t\t<h:inputText/>\r\n" +
				"\t\t\t<h:message/>\r\n" +
				"\t\t</h:panelGroup>\r\n" +
				"\t\t<h:outputText/>\r\n" +
				"\t\t<h:outputLabel value=\"Baz:\"/>\r\n" +
				"\t\t<h:panelGroup>\r\n" +
				"\t\t\t<h:inputText/>\r\n" +
				"\t\t\t<h:message/>\r\n" +
				"\t\t</h:panelGroup>\r\n" +
				"\t\t<h:outputText/>\r\n" +
				"\t</h:panelGrid>\r\n";

		assertEquals( result, writer.toString() );

		writer = new StringWriter();
		metawidget.write( writer, 5 );

		result = "\t\t\t\t\t<h:panelGrid columns=\"3\">\r\n" +
				"\t\t\t\t\t\t<h:outputLabel value=\"Bar:\"/>\r\n" +
				"\t\t\t\t\t\t<h:panelGroup>\r\n" +
				"\t\t\t\t\t\t\t<h:inputText/>\r\n" +
				"\t\t\t\t\t\t\t<h:message/>\r\n" +
				"\t\t\t\t\t\t</h:panelGroup>\r\n" +
				"\t\t\t\t\t\t<h:outputText/>\r\n" +
				"\t\t\t\t\t\t<h:outputLabel value=\"Baz:\"/>\r\n" +
				"\t\t\t\t\t\t<h:panelGroup>\r\n" +
				"\t\t\t\t\t\t\t<h:inputText/>\r\n" +
				"\t\t\t\t\t\t\t<h:message/>\r\n" +
				"\t\t\t\t\t\t</h:panelGroup>\r\n" +
				"\t\t\t\t\t\t<h:outputText/>\r\n" +
				"\t\t\t\t\t</h:panelGrid>\r\n";

		assertEquals( result, writer.toString() );

		writer = new StringWriter();
		metawidget.write( writer, 5 );

		result = "<h:panelGrid columns=\"3\">\r\n" +
				"\t\t\t\t\t\t<h:outputLabel value=\"Bar:\"/>\r\n" +
				"\t\t\t\t\t\t<h:panelGroup>\r\n" +
				"\t\t\t\t\t\t\t<h:inputText/>\r\n" +
				"\t\t\t\t\t\t\t<h:message/>\r\n" +
				"\t\t\t\t\t\t</h:panelGroup>\r\n" +
				"\t\t\t\t\t\t<h:outputText/>\r\n" +
				"\t\t\t\t\t\t<h:outputLabel value=\"Baz:\"/>\r\n" +
				"\t\t\t\t\t\t<h:panelGroup>\r\n" +
				"\t\t\t\t\t\t\t<h:inputText/>\r\n" +
				"\t\t\t\t\t\t\t<h:message/>\r\n" +
				"\t\t\t\t\t\t</h:panelGroup>\r\n" +
				"\t\t\t\t\t\t<h:outputText/>\r\n" +
				"\t\t\t\t\t</h:panelGrid>";

		assertEquals( result, writer.toString().trim() );
	}

	public void testSimpleType() {

		StaticHtmlMetawidget metawidget = new StaticHtmlMetawidget();
		metawidget.setValue( "#{foo}" );
		metawidget.setPath( Foo.class.getName() );

		String result = "<h:panelGrid columns=\"3\">\r\n" +
				"\t<h:outputLabel for=\"fooBar\" value=\"Bar:\"/>\r\n" +
				"\t<h:panelGroup>\r\n" +
				"\t\t<h:inputText id=\"fooBar\" value=\"#{foo.bar}\"/>\r\n" +
				"\t\t<h:message for=\"fooBar\"/>\r\n" +
				"\t</h:panelGroup>\r\n" +
				"\t<h:outputText/>\r\n" +
				"\t<h:outputLabel for=\"fooBaz\" value=\"Baz:\"/>\r\n" +
				"\t<h:panelGroup>\r\n" +
				"\t\t<h:inputText id=\"fooBaz\" value=\"#{foo.baz}\"/>\r\n" +
				"\t\t<h:message for=\"fooBaz\"/>\r\n" +
				"\t</h:panelGroup>\r\n" +
				"\t<h:outputText/>\r\n" +
				"</h:panelGrid>\r\n";

		StringWriter writer = new StringWriter();
		metawidget.write( writer, 0 );
		assertEquals( result, writer.toString() );

		Map<String, String> namespaces = metawidget.getNamespaces();
		assertEquals( "http://java.sun.com/jsf/html", namespaces.get( "h" ) );
		assertEquals( 1, namespaces.size() );
	}

	public void testNestedType() {

		StaticHtmlMetawidget metawidget = new StaticHtmlMetawidget();
		metawidget.setValue( "#{foo}" );
		metawidget.setPath( NestedFoo.class.getName() );

		String result = "<h:panelGrid columns=\"3\">\r\n" +
				"\t<h:outputLabel for=\"fooAbc\" value=\"Abc:\"/>\r\n" +
				"\t<h:panelGroup>\r\n" +
				"\t\t<h:inputText id=\"fooAbc\" value=\"#{foo.abc}\"/>\r\n" +
				"\t\t<h:message for=\"fooAbc\"/>\r\n" +
				"\t</h:panelGroup>\r\n" +
				"\t<h:outputText/>\r\n" +
				"\t<h:outputLabel for=\"fooNestedFoo\" value=\"Nested Foo:\"/>\r\n" +
				"\t<h:panelGrid columns=\"3\" id=\"fooNestedFoo\">\r\n" +
				"\t\t<h:outputLabel for=\"fooNestedFooBar\" value=\"Bar:\"/>\r\n" +
				"\t\t<h:panelGroup>\r\n" +
				"\t\t\t<h:inputText id=\"fooNestedFooBar\" value=\"#{foo.nestedFoo.bar}\"/>\r\n" +
				"\t\t\t<h:message for=\"fooNestedFooBar\"/>\r\n" +
				"\t\t</h:panelGroup>\r\n" +
				"\t\t<h:outputText/>\r\n" +
				"\t\t<h:outputLabel for=\"fooNestedFooBaz\" value=\"Baz:\"/>\r\n" +
				"\t\t<h:panelGroup>\r\n" +
				"\t\t\t<h:inputText id=\"fooNestedFooBaz\" value=\"#{foo.nestedFoo.baz}\"/>\r\n" +
				"\t\t\t<h:message for=\"fooNestedFooBaz\"/>\r\n" +
				"\t\t</h:panelGroup>\r\n" +
				"\t\t<h:outputText/>\r\n" +
				"\t</h:panelGrid>\r\n" +
				"\t<h:outputText/>\r\n" +
				"</h:panelGrid>\r\n";

		StringWriter writer = new StringWriter();
		metawidget.write( writer, 0 );
		assertEquals( result, writer.toString() );

		Map<String, String> namespaces = metawidget.getNamespaces();
		assertEquals( "http://java.sun.com/jsf/html", namespaces.get( "h" ) );
		assertEquals( 1, namespaces.size() );
	}

	public void testOutputTextLayoutDecorator() {

		StaticHtmlMetawidget metawidget = new StaticHtmlMetawidget();
		metawidget.setValue( "#{foo}" );
		metawidget.setPath( Sections.class.getName() );

		String result = "<h:panelGrid columns=\"3\">\r\n" +
				"\t<h:outputText value=\"Section #1\"/>\r\n" +
				"\t<h:outputText/>\r\n" +
				"\t<h:outputText/>\r\n" +
				"\t<h:outputLabel for=\"fooAbc\" value=\"Abc:\"/>\r\n" +
				"\t<h:panelGroup>\r\n" +
				"\t\t<h:inputText id=\"fooAbc\" value=\"#{foo.abc}\"/>\r\n" +
				"\t\t<h:message for=\"fooAbc\"/>\r\n" +
				"\t</h:panelGroup>\r\n" +
				"\t<h:outputText/>\r\n" +
				"\t<h:outputText value=\"Section #2\"/>\r\n" +
				"\t<h:outputText/>\r\n" +
				"\t<h:outputText/>\r\n" +
				"\t<h:outputLabel for=\"fooDef\" value=\"Def:\"/>\r\n" +
				"\t<h:panelGroup>\r\n" +
				"\t\t<h:inputText id=\"fooDef\" value=\"#{foo.def}\"/>\r\n" +
				"\t\t<h:message for=\"fooDef\"/>\r\n" +
				"\t</h:panelGroup>\r\n" +
				"\t<h:outputText/>\r\n" +
				"</h:panelGrid>\r\n";

		StringWriter writer = new StringWriter();
		metawidget.write( writer, 0 );
		assertEquals( result, writer.toString() );
	}

	//
	// Inner class
	//

	public static class Foo {

		public String getBar() {

			return null;
		}

		public void setBar( @SuppressWarnings( "unused" ) String bar ) {

			// Do nothing
		}

		public String getBaz() {

			return null;
		}

		public void setBaz( @SuppressWarnings( "unused" ) String baz ) {

			// Do nothing
		}
	}

	public static class NestedFoo {

		public String getAbc() {

			return null;
		}

		public void setAbc( @SuppressWarnings( "unused" ) String abc ) {

			// Do nothing
		}

		public Foo getNestedFoo() {

			return null;
		}

		public void setNestedFoo( @SuppressWarnings( "unused" ) Foo nestedFoo ) {

			// Do nothing
		}
	}

	public static class Sections {

		@UiSection( "Section #1" )
		public String getAbc() {

			return null;
		}

		public void setAbc( @SuppressWarnings( "unused" ) String abc ) {

			// Do nothing
		}

		@UiSection( { "Section #1", "Section #2" } )
		public String getDef() {

			return null;
		}

		public void setDef( @SuppressWarnings( "unused" ) String def ) {

			// Do nothing
		}
	}
}
