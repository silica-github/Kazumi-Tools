##Kazumi-Tools
自用 Android 工具类，存档用意。
  
###AntiMultipleClicksUtil
防止多次点击产生重复点击事件。  

<pre>
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
</pre>