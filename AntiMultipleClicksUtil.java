import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by KAZUMI on 2017-03-06.
 * ====
 * 防止多次点击产生重复点击事件.
 */

/*
    使用方法:
    ====
    btn_test.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // 如果 AntiMultipleClicksUtil.check() 为 true, 拦截后续操作
            if(AntiMultipleClicksUtil.check()){
                return;
            }

            // 后续操作
            Log.i(TAG, "这是测试文本, 在 1000 ms 内连续点击只能输出一次.")

        }
    });
 */

public class AntiMultipleClicksUtil {

    private static List<OnClickUtil> utils = new ArrayList<>();

    public static boolean check(Object o) {
        String flag = null;
        if (o == null)
            flag = Thread.currentThread().getStackTrace()[2].getMethodName();
        else
            flag = o.toString();
        for (OnClickUtil util : utils) {
            if (util.getMethodName().equals(flag)) {
                return util.check();
            }
        }
        OnClickUtil clickUtil = new OnClickUtil(flag);
        utils.add(clickUtil);
        return clickUtil.check();
    }

    public static boolean check() {
        return check(null);
    }

    private static class OnClickUtil {

        // 最短点击间隔 (ms)
        public static final int MIN_CLICK_DELAY_TIME = 1000;

        private String methodName;
        private long lastClickTime = 0;

        public OnClickUtil(String methodName) {
            this.methodName = methodName;
        }

        public String getMethodName() {
            return methodName;
        }

        public boolean check() {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                return false;
            } else {
                return true;
            }
        }
    }
}
