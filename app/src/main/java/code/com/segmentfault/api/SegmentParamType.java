package code.com.segmentfault.api;

/**
 * Author: lihui1
 * Date: 2018/4/15
 * Desc:
 */

public enum  SegmentParamType {

    SEGMENT_PARAM_ISDEBUG("isDebug"),
    SEGMENT_PARAM_SESSIONID("sessionId"),
    SEGMENT_PARAM_CLIENT_TYPE("clientType"),
    SEGMENT_PARAM_CHANNEL("channel"),
    SEGMENT_PARAM_SIGN_ACTIVITY("signActivity"),
    SEGMENT_PARAM_LOGIN_ACTIVITY("loginActivity"),
    SEGMENT_PARAM_BANNER_JUMP("bannerJump"),
    SEGMENT_PARAM_SHARE_TO_THIRD_PARTNER("shareToThirdPartner"),
    SEGMENT_PARAM_HAS_LOGIN("hasLogin"),
    SEGMENT_PARAM_PARENTS_API_VERSION("parentsApiVersion"),
    SEGMENT_PARAM_COMMUNITY_URL("communityURL"),
    SEGMENT_PARAM_SHARE_APP_ICON("shareAppIcon"),
    SEGMENT_PARAM_USER_ID("userId");


    private String paramName;

    SegmentParamType(String paramName) {
        this.paramName = paramName;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
}
