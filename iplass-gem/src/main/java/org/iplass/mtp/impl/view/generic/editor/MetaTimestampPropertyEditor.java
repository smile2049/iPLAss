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

import org.iplass.mtp.impl.util.ObjectUtil;
import org.iplass.mtp.view.generic.editor.DateTimePropertyEditor.MinIntereval;
import org.iplass.mtp.view.generic.editor.DateTimePropertyEditor.TimeDispRange;
import org.iplass.mtp.view.generic.editor.PropertyEditor;
import org.iplass.mtp.view.generic.editor.TimestampPropertyEditor;

/**
 * 日時型プロパティエディタのメタデータ
 * @author lis3wg
 */
public class MetaTimestampPropertyEditor extends MetaDateTimePropertyEditor {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1640054951421530441L;

	public static MetaTimestampPropertyEditor createInstance(PropertyEditor editor) {
		return new MetaTimestampPropertyEditor();
	}

	/** 時間の表示範囲 */
	private TimeDispRange dispRange;

	/** 分の間隔 */
	private MinIntereval interval;

	/** 現在日付設定ボタン表示可否 */
	private boolean hideButtonPanel;

	/** 時間のデフォルト値補完を行わない */
	private boolean notFillTime;

	/** DatetimePickerの利用有無 */
	private boolean useDatetimePicker;

	/** 曜日を表示 */
	private boolean showWeekday;

	/**
	 * 時間の表示範囲を取得します。
	 * @return 時間の表示範囲
	 */
	public TimeDispRange getDispRange() {
		return dispRange;
	}

	/**
	 * 時間の表示範囲を設定します。
	 * @param dispRange 時間の表示範囲
	 */
	public void setDispRange(TimeDispRange dispRange) {
		this.dispRange = dispRange;
	}

	/**
	 * 分の間隔を取得します。
	 * @return 分の間隔
	 */
	public MinIntereval getInterval() {
		return interval;
	}

	/**
	 * 分の間隔を設定します。
	 * @param interval 分の間隔
	 */
	public void setInterval(MinIntereval interval) {
		this.interval = interval;
	}

	/**
	 * 現在日付設定ボタン表示可否を取得します。
	 * @return 現在日付設定ボタン表示可否
	 */
	public boolean isHideButtonPanel() {
	    return hideButtonPanel;
	}

	/**
	 * 現在日付設定ボタン表示可否を設定します。
	 * @param hideButtonPanel 現在日付設定ボタン表示可否
	 */
	public void setHideButtonPanel(boolean hideButtonPanel) {
	    this.hideButtonPanel = hideButtonPanel;
	}

	/**
	 * 時間のデフォルト値補完を行わないを取得します。
	 * @return 時間のデフォルト値補完を行わない
	 */
	public boolean isNotFillTime() {
	    return notFillTime;
	}

	/**
	 * 時間のデフォルト値補完を行わないを設定します。
	 * @param notFillTime 時間のデフォルト値補完を行わない
	 */
	public void setNotFillTime(boolean notFillTime) {
	    this.notFillTime = notFillTime;
	}

	/**
	 * DatetimePickerの利用有無を取得します。
	 * @return DatetimePickerの利用有無
	 */
	public boolean isUseDatetimePicker() {
		return useDatetimePicker;
	}

	/**
	 * DatetimePickerの利用有無を設定します。
	 * @param displayDateTimePicker DatetimePickerの利用有無
	 */
	public void setUseDatetimePicker(boolean useDatetimePicker) {
		this.useDatetimePicker = useDatetimePicker;
	}

	/**
	 * @return showWeekday
	 */
	public boolean isShowWeekday() {
		return showWeekday;
	}

	/**
	 * @param showWeekday セットする showWeekday
	 */
	public void setShowWeekday(boolean showWeekday) {
		this.showWeekday = showWeekday;
	}

	@Override
	public void applyConfig(PropertyEditor editor) {
		super.fillFrom(editor);

		TimestampPropertyEditor pe = (TimestampPropertyEditor) editor;
		this.dispRange = pe.getDispRange();
		this.interval = pe.getInterval();
		this.hideButtonPanel = pe.isHideButtonPanel();
		this.notFillTime = pe.isNotFillTime();
		this.useDatetimePicker = pe.isUseDatetimePicker();
		this.showWeekday = pe.isShowWeekday();
	}

	@Override
	public PropertyEditor currentConfig(String propertyName) {
		TimestampPropertyEditor editor = new TimestampPropertyEditor();
		super.fillTo(editor);

		editor.setDispRange(this.dispRange);
		editor.setInterval(this.interval);
		editor.setHideButtonPanel(hideButtonPanel);
		editor.setNotFillTime(this.notFillTime);
		editor.setUseDatetimePicker(this.useDatetimePicker);
		editor.setShowWeekday(this.showWeekday);
		return editor;
	}

	@Override
	public MetaTimestampPropertyEditor copy() {
		return ObjectUtil.deepCopy(this);
	}

}
