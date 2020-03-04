/*
 * Copyright (C) 2020 INFORMATION SERVICES INTERNATIONAL - DENTSU, LTD. All Rights Reserved.
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
package org.iplass.mtp.impl.webhook;

import java.io.Serializable;
import java.sql.Timestamp;

import org.iplass.mtp.auth.login.Credential;
import org.iplass.mtp.auth.token.AuthTokenInfo;
import org.iplass.mtp.impl.auth.authenticate.token.AuthToken;
import org.iplass.mtp.impl.auth.authenticate.token.AuthTokenHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * データベースでwebHookのパスワードや、トークンを管理するシークレットサービス
 * rdb使用です。
 * 
 * @author lisf06
 *
 */
public class WebHookAuthTokenHandler extends AuthTokenHandler{
	private static Logger logger = LoggerFactory.getLogger(WebHookAuthTokenHandler.class);
	
	//WebHookAuthToken Type
	public static final String BASIC_AUTHENTICATION_TYPE = "WHBA";
	public static final String BEARER_AUTHENTICATION_TYPE = "WHBT";
	public static final String HMAC_AUTHENTICATION_TYPE = "WHHM";
	
	public static final String TYPE_WEBHOOK_AUTHTOKEN_HANDLER="WEBHOOKATH";
	@Override
	protected Serializable createDetails(String seriesString, String tokenString, String userUniqueId,
			String policyName, AuthTokenInfo tokenInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthTokenInfo toAuthTokenInfo(AuthToken authToken) {
		WebHookAuthTokenInfo info = new WebHookAuthTokenInfo();
		info.setType(authToken.getType());
		info.setKey(authToken.getSeries());
		
		return info;
	}

	//retrieve token with tenantid, series and type. from : super.getBySeries(int tenantId, String type, String series)
	
	//creeateToken, from : super.create(AuthToken token)
	/**
	 * database->raw data
	 * 対応の認証の情報Stringを取得
	 * basicはbase64(userName:Password)形式、そのまま使える
	 * */
	public String getSecret(final int tenantId,final String series,final String type) {
		checkTypeValidity(type);
		AuthToken token = authTokenStore().getBySeries(tenantId, type, series);
		if(token ==null) {
			return null;
		}
		return token.getToken();
	}
	
	/**
	 * raw data -> database
	 * 外側でseries生成してください
	 */
	public void insertSecret(final int tenantId, final String type, final String metaDataId, final String series, final String tokenSecret) {
		checkTypeValidity(type);
		Timestamp startDate = new Timestamp(java.lang.System.currentTimeMillis());
		AuthToken token = new AuthToken(tenantId, type, metaDataId, series, tokenSecret, "", startDate, null);
		authTokenStore().create(token);
	}
	
	/**
	 * raw data -> database
	 */
	public void updateSecret(final int tenantId, final String type, final String metaDataId, final String series, final String tokenSecret) {
		checkTypeValidity(type);
		AuthToken oldToken = authTokenStore().getBySeries(tenantId, type, series);
		Timestamp startDate = new Timestamp(java.lang.System.currentTimeMillis());
		AuthToken newToken = new AuthToken(tenantId, type, metaDataId, series, tokenSecret, "", startDate, null);
		authTokenStore().update(newToken, oldToken);
	}
	
	/**
	 * raw data -> database
	 * 特定のデータを削除する
	 */
	public void deleteSecret(final int tenantId, final String type, final String series) {
		checkTypeValidity(type);
		authTokenStore().deleteBySeries(tenantId, type, series);
	}
	/**
	 * do not use, TODO:marked for delete
	 * */
	@Override
	public Credential toCredential(AuthToken newToken) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * throw runtime Exception if invalid.
	 **/
	private boolean checkTypeValidity(String type) {
		if (BASIC_AUTHENTICATION_TYPE.equals(type)) {
			return true;
		}
		if (BEARER_AUTHENTICATION_TYPE.equals(type)) {
			return true;
		}
		if (HMAC_AUTHENTICATION_TYPE.equals(type)) {
			return true;
		}
		//セキュリティ情報に関するDB交互なので、見送りはできない
		throw new RuntimeException("The type is not an acceptable WebHookAuthToken Type");
	}
}