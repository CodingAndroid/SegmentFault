package code.com.segmentfault;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import java.util.HashMap;

import code.com.segmentfault.api.SegmentParamType;

/**
 * Author: lihui1
 * Date: 2018/4/15
 * Desc:
 */

public class SegmentApplication extends Application{

    private static SegmentApplication mSegmentApplication = null;
    private Context mContext;
    private HashMap<SegmentParamType, Object> paramsMap;

    @Override
    public void onCreate() {
        super.onCreate();
        mSegmentApplication = this;
        Stetho.initializeWithDefaults(this);
    }

    public static SegmentApplication getInstance(){
        if (mSegmentApplication == null){
            mSegmentApplication = new SegmentApplication();
        }
        return mSegmentApplication;
    }

    public Context getContext(){
        return mContext;
    }

    public boolean isDebugMode(){
        if (paramsMap != null){
            return (boolean) paramsMap.get(SegmentParamType.SEGMENT_PARAM_ISDEBUG);
        }
        return true;
    }

    public String getSessionId(){
        if (paramsMap != null && paramsMap.containsKey(SegmentParamType.SEGMENT_PARAM_SESSIONID)){
            return (String) paramsMap.get(SegmentParamType.SEGMENT_PARAM_SESSIONID);
        }
        return "";
    }

    public String getUserId(){
        if (paramsMap != null && paramsMap.containsKey(SegmentParamType.SEGMENT_PARAM_USER_ID)) {
            return (String) paramsMap.get(SegmentParamType.SEGMENT_PARAM_USER_ID);
        }
        return "0";
    }
}
