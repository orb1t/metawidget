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

package org.metawidget.faces.component.html.layout.richfaces;

import javax.faces.component.UIComponent;

import org.metawidget.faces.component.UIMetawidget;
import org.metawidget.layout.delegate.DelegateLayoutConfig;
import org.metawidget.util.simple.ObjectUtils;

/**
 * Configures a TabPanelLayoutConfig prior to use. Once instantiated, Layouts are immutable.
 *
 * @author Richard Kennard
 */

public class TabPanelLayoutConfig
	extends DelegateLayoutConfig<UIComponent, UIMetawidget>
{
	//
	// Private members
	//

	private String	mHeaderAlignment	= "left";

	//
	// Public methods
	//

	public String getHeaderAlignment()
	{
		return mHeaderAlignment;
	}

	/**
	 * @return this, as part of a fluent interface
	 */

	public TabPanelLayoutConfig setHeaderAlignment( String headerAlignment )
	{
		mHeaderAlignment = headerAlignment;

		return this;
	}

	// TODO: unit test TabPanelLayoutConfig

	@Override
	public boolean equals( Object that )
	{
		if ( !( that instanceof RichFacesLayoutConfig ) )
			return false;

		if ( !ObjectUtils.nullSafeEquals( mHeaderAlignment, ( (TabPanelLayoutConfig) that ).mHeaderAlignment ))
			return false;

		return super.equals( that );
	}

	@Override
	public int hashCode()
	{
		int hashCode = super.hashCode();
		hashCode ^= ObjectUtils.nullSafeHashCode( mHeaderAlignment );

		return hashCode;
	}
}
