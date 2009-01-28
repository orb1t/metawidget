// Metawidget
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

package org.metawidget.faces.component.html.richfaces;

import static org.metawidget.inspector.InspectionResultConstants.*;
import static org.metawidget.inspector.faces.FacesInspectionResultConstants.*;

import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;

import org.metawidget.faces.component.html.HtmlMetawidget;
import org.metawidget.util.ClassUtils;
import org.richfaces.component.UICalendar;
import org.richfaces.component.html.HtmlInputNumberSlider;
import org.richfaces.component.html.HtmlInputNumberSpinner;

/**
 * Metawidget for RichFaces environments.
 * <p>
 * Automatically creates native RichFaces UIComponents, such as <code>HtmlCalendar</code>
 * and <code>HtmlInputNumberSlider</code>, to suit the inspected fields.
 *
 * @author Richard Kennard
 */

public class RichFacesMetawidget
	extends HtmlMetawidget
{
	//
	// Protected methods
	//

	/**
	 * Purely creates the widget. Does not concern itself with the widget's id, value binding or
	 * preparing metadata for the renderer.
	 *
	 * @return the widget to use in non-read-only scenarios
	 */

	@Override
	protected UIComponent buildActiveWidget( String elementName, Map<String, String> attributes )
		throws Exception
	{
		// Not for RichFaces

		if ( TRUE.equals( attributes.get( HIDDEN )) )
			return super.buildActiveWidget( elementName, attributes );

		if ( attributes.containsKey( FACES_LOOKUP ) || attributes.containsKey( LOOKUP ))
			return super.buildActiveWidget( elementName, attributes );

		Application application = getFacesContext().getApplication();
		String type = attributes.get( TYPE );

		if ( type == null )
			return super.buildActiveWidget( elementName, attributes );

		Class<?> clazz = ClassUtils.niceForName( type );

		if ( clazz != null )
		{
			// Primitives

			if ( clazz.isPrimitive() )
			{
				// Not for RichFaces

				if ( boolean.class.equals( clazz ) || char.class.equals( clazz ))
					return super.buildActiveWidget( elementName, attributes );

				// Ranged

				String minimumValue = attributes.get( MINIMUM_VALUE );
				String maximumValue = attributes.get( MAXIMUM_VALUE );

				if ( minimumValue != null && !"".equals( minimumValue ) && maximumValue != null && !"".equals( maximumValue ))
				{
					HtmlInputNumberSlider slider = (HtmlInputNumberSlider) application.createComponent( "org.richfaces.inputNumberSlider" );
					slider.setMinValue( minimumValue );
					slider.setMaxValue( maximumValue );

					return slider;
				}

				// Not-ranged

				HtmlInputNumberSpinner spinner = (HtmlInputNumberSpinner) application.createComponent( "org.richfaces.inputNumberSpinner" );
				spinner.setCycled( false );

				if ( byte.class.equals( clazz ))
				{
					spinner.setMinValue( String.valueOf( Byte.MIN_VALUE ));
					spinner.setMaxValue( String.valueOf( Byte.MAX_VALUE ));
				}
				else if ( short.class.equals( clazz ) )
				{
					spinner.setMinValue( String.valueOf( Short.MIN_VALUE ));
					spinner.setMaxValue( String.valueOf( Short.MAX_VALUE ));
				}
				else if ( int.class.equals( clazz ) )
				{
					// TODO: test this

					if ( minimumValue != null && !"".equals( minimumValue ))
						spinner.setMinValue( minimumValue );
					else
						spinner.setMinValue( String.valueOf( Integer.MIN_VALUE ));

					if ( maximumValue != null && !"".equals( maximumValue ))
						spinner.setMaxValue( maximumValue );
					else
						spinner.setMaxValue( String.valueOf( Integer.MAX_VALUE ));
				}
				else if ( long.class.equals( clazz ) )
				{
					spinner.setMinValue( String.valueOf( Long.MIN_VALUE ));
					spinner.setMaxValue( String.valueOf( Long.MAX_VALUE ));
				}
				else if ( float.class.equals( clazz ) )
				{
					spinner.setMinValue( String.valueOf( -Float.MAX_VALUE ));
					spinner.setMaxValue( String.valueOf( Float.MAX_VALUE ));
					spinner.setStep( "0.1" );
				}
				else if ( double.class.equals( clazz ) )
				{
					spinner.setMinValue( String.valueOf( -Double.MAX_VALUE ));
					spinner.setMaxValue( String.valueOf( Double.MAX_VALUE ));
					spinner.setStep( "0.1" );
				}

				return spinner;
			}

			// Dates
			//
			// Note: when http://jira.jboss.org/jira/browse/RF-2023 gets implemented, that
			// would allow external, app-level configuration of this Calendar

			if ( Date.class.isAssignableFrom( clazz ) )
			{
				UICalendar calendar = (UICalendar) application.createComponent( "org.richfaces.Calendar" );

				if ( attributes.get( DATETIME_PATTERN ) != null )
					calendar.setDatePattern( attributes.get( DATETIME_PATTERN ));

				if ( attributes.get( LOCALE ) != null )
					calendar.setLocale( new Locale( attributes.get( LOCALE ) ) );

				if ( attributes.get( TIME_ZONE ) != null )
					calendar.setTimeZone( TimeZone.getTimeZone( attributes.get( TIME_ZONE )));

				return calendar;
			}

			// Object primitives

			if ( Number.class.isAssignableFrom( clazz ) )
			{
				// Ranged

				String minimumValue = attributes.get( MINIMUM_VALUE );
				String maximumValue = attributes.get( MAXIMUM_VALUE );

				if ( minimumValue != null && !"".equals( minimumValue ) && maximumValue != null && !"".equals( maximumValue ))
				{
					HtmlInputNumberSlider slider = (HtmlInputNumberSlider) application.createComponent( "org.richfaces.inputNumberSlider" );
					slider.setMinValue( minimumValue );
					slider.setMaxValue( maximumValue );

					return slider;
				}

				// Not-ranged
				//
				// Until https://jira.jboss.org/jira/browse/RF-4450 is fixed, do not use
				// HtmlInputNumberSpinner for nullable numbers
			}
		}

		// Not for RichFaces

		return super.buildActiveWidget( elementName, attributes );
	}
}
