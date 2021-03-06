/*
 * Copyright (C) 2011 INFORMATION SERVICES INTERNATIONAL - DENTSU, LTD. All Rights Reserved.
 * 
 * Unless you have purchased a commercial license,
 * the following license terms apply:
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.iplass.mtp.impl.view.generic.editor;

import java.util.ArrayList;
import java.util.List;

import org.iplass.mtp.impl.i18n.I18nUtil;
import org.iplass.mtp.impl.i18n.MetaLocalizedString;
import org.iplass.mtp.impl.util.ObjectUtil;
import org.iplass.mtp.view.generic.editor.BooleanPropertyEditor;
import org.iplass.mtp.view.generic.editor.PropertyEditor;
import org.iplass.mtp.view.generic.editor.BooleanPropertyEditor.BooleanDisplayType;

/**
 * 真偽型プロパティエディタのメタデータ
 * @author lis3wg
 */
public class MetaBooleanPropertyEditor extends MetaPrimitivePropertyEditor {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = -480791731251388954L;

	public static MetaBooleanPropertyEditor createInstance(PropertyEditor editor) {
		return new MetaBooleanPropertyEditor();
	}

	/** 表示タイプ */
	private BooleanDisplayType displayType;

	/** 真の表示ラベル */
	private String trueLabel;

	/** 真の表示ラベル多言語設定情報 */
	private List<MetaLocalizedString> localizedTrueLabelList = new ArrayList<MetaLocalizedString>();

	/** 偽の表示ラベル */
	private String falseLabel;

	/** 偽の表示ラベル多言語設定情報 */
	private List<MetaLocalizedString> localizedFalseLabelList = new ArrayList<MetaLocalizedString>();

	/**
	 * 表示タイプを取得します。
	 * @return 表示タイプ
	 */
	public BooleanDisplayType getDisplayType() {
		return displayType;
	}

	/**
	 * 表示タイプを設定します。
	 * @param displayType 表示タイプ
	 */
	public void setDisplayType(BooleanDisplayType displayType) {
		this.displayType = displayType;
	}

	/**
	 * 真の表示ラベルを取得します。
	 * @return 真の表示ラベル
	 */
	public String getTrueLabel() {
		return trueLabel;
	}

	/**
	 * 真の表示ラベルを設定します。
	 * @param trueLabel 真の表示ラベル
	 */
	public void setTrueLabel(String trueLabel) {
		this.trueLabel = trueLabel;
	}

	/**
	 * 偽の表示ラベルを取得します。
	 * @return 偽の表示ラベル
	 */
	public String getFalseLabel() {
		return falseLabel;
	}

	/**
	 * 偽の表示ラベルを設定します。
	 * @param falseLabel 偽の表示ラベル
	 */
	public void setFalseLabel(String falseLabel) {
		this.falseLabel = falseLabel;
	}

	/**
	 * 真の表示ラベル多言語設定情報を取得します。
	 * @return リスト
	 */
	public List<MetaLocalizedString> getLocalizedTrueLabelList() {
		return localizedTrueLabelList;
	}

	/**
	 * 真の表示ラベル多言語設定情報を設定します。
	 * @param リスト
	 */
	public void setLocalizedTrueLabelList(List<MetaLocalizedString> localizedTrueLabelList) {
		this.localizedTrueLabelList = localizedTrueLabelList;
	}

	/**
	 * 偽の表示ラベル多言語設定情報を取得します。
	 * @return リスト
	 */
	public List<MetaLocalizedString> getLocalizedFalseLabelList() {
		return localizedFalseLabelList;
	}

	/**
	 * 偽の表示ラベル多言語設定情報を設定します。
	 * @param リスト
	 */
	public void setLocalizedFalseLabelList(List<MetaLocalizedString> localizedFalseLabelList) {
		this.localizedFalseLabelList = localizedFalseLabelList;
	}

	@Override
	public MetaBooleanPropertyEditor copy() {
		return ObjectUtil.deepCopy(this);
	}

	@Override
	public void applyConfig(PropertyEditor editor) {
		super.fillFrom(editor);

		BooleanPropertyEditor e = (BooleanPropertyEditor) editor;
		displayType = e.getDisplayType();
		trueLabel = e.getTrueLabel();
		falseLabel = e.getFalseLabel();

		localizedTrueLabelList = I18nUtil.toMeta(e.getLocalizedTrueLabelList());
		localizedFalseLabelList = I18nUtil.toMeta(e.getLocalizedFalseLabelList());
	}

	@Override
	public PropertyEditor currentConfig(String propertyName) {
		BooleanPropertyEditor editor = new BooleanPropertyEditor();
		super.fillTo(editor);
		editor.setDisplayType(displayType);
		editor.setTrueLabel(trueLabel);
		editor.setFalseLabel(falseLabel);
		editor.setLocalizedTrueLabelList(I18nUtil.toDef(localizedTrueLabelList));
		editor.setLocalizedFalseLabelList(I18nUtil.toDef(localizedFalseLabelList));
		return editor;
	}

}
