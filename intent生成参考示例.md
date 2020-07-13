## intent生成参考示例
Android 开发工程师生成 intent，可以在 Android 代码中定义一个 Intent，然后通过 Intent.toUri(Intent.URI_INTENT_SCHEME) 获取对应的 Intent Uri。

### 1 生成Intent

```
Intent intent = new Intent();
```
### 2 设置 package（可选）
如果设置了 package，则表示目标应用必须是 package 所对应的应用。

```
intent.setPackage("com.getui.demo");
```
### 3 设置 component (必选)
component 为目标页面的准确 activity 路径，component 中包含应用包名和 activity 类的全路径。
 
```
 intent.setComponent(new ComponentName("com.getui.demo","com.getui.demo.TestActivity"));
```
### 4 设置 launchFlags (不起作用)
launcherFlags 可指定在启动 activity 时的启动方式，个推 sdk 以及厂商 sdk 在启动 activity 时会自动设置为 FLAG_ACTIVITY_NEW_TASK，所以该设置没有实际意义。

### 5 设置参数
可通过 intent.putExtra 方法设置一系列的参数。
 
```
intent.putExtra("stringType", "string");
intent.putExtra("booleanType", true);
intent.putExtra("doubleType", 1.0);
intent.putExtra("floatType", 1.0f);
intent.putExtra("longType", 1L);
intent.putExtra("intType", 1);
// 还有其他类型的参数可以设置，通过查找`putExtra`方法的重载方法即可
```
>注：由于**部分厂商**只支持 String 类型的参数，设置其它类型参数，App 端接收以后，会强转为 String 类型，所以建议参数统一设置为 String 类型。参数可以分开设置，也可以合并为一个 json 格式数据。

### 6 设置action
如果intent-filter里面有data属性，需要新建过滤器添加action配置，以免影响跳转。

```
//设置action
intent.setAction("android.intent.action.gtpush");
``` 

AndroidManifest.xml 配置如下：

```
<!-- android:exported="true" 必须配置，否则部分机型上无法弹通知，影响消息的跳转-->
<activity android:name="com.getui.demo.TestActivity"
	android:exported="true">
    <intent-filter>
    	<!-- category 为必须设置项 设置为 android.intent.category.DEFAULT 即可-->
    	<category android:name="android.intent.category.DEFAULT" />
    	<action android:name="android.intent.action.gtpush" />
     </intent-filter>
 </activity>
```

### 7 生成 intent uri

```
Log.d(TAG, intent.toUri(Intent.URI_INTENT_SCHEME));
```  

```
打印结果为：intent:#Intent;launchFlags=0x10000000;action=android.intent.action.gtpush;package=com.getui.demo;component=com.getui.demo/com.getui.demo.TestActivity;f.floatType=1.0;l.longType=1;B.booleanType=true;S.stringType=string;d.doubleType=1.0;i.intType=1;end
```
**将生成的 intent uri 交给服务端开发人员，在对应模板的 setIntent 方法中设置即可。**

>注：<br> 1、S 大写是 string 类型，s小写是short类型，B 大写是 boolean 类型，b 小写是 byte 类型，其它均小写即可；<br>2、设置的参数如有特殊字符，比如 Scheme 链接含有#符号，建议 UrlEncode 编码处理，参数在经过Android 系统接收后，会自动进行解码处理（经测试，intent 参数里面有 % 符号，客户端接收参数会乱码；intent 参数里面有 # 和 ; 符号，会影响跳转，UrlEncode 编码处理后正常。）；<br>3、如果遇到点击第二条通知，页面不能正常跳转的情况，建议设置  launchFlags为0x04000000 测试，这个一般是 activity 启动模式的问题，参数会在 activity 的 onCreate 或 onNewIntent 方法回调。