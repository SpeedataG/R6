# SpdataR6<br>
###文件说明<br>
	“Android Studio源码”文件夹中存放了r6的android stuido版开发源码，包含了lib文件。
    “eclipse源码”文件夹内分别存放了r6demo的源码以及其需要导入的lib文件“r6lib”。安装包文件“r6demo.apk”是r6的demo文件。
    程序的Api链接：https://www.showdoc.cc/11377?page_id=102430
###功能介绍<br>
	本程序适用于思必拓手持终端设备中，装有高频读卡模块的设备。能识别读取的非接卡种类有：CPUA、CPUB、ISO15693、Mifare、Ultralight等。
###使用说明<br>
	在任一支持高频读卡的思必拓手持终端设备中，安装”r6demo.apk”文件后，会生成一个名为“R6”的程序图标。
	点击进入后，在界面上方有五种卡片的对应按钮。其中，选择CPU卡后，继续在本界面进行操作，选择其他卡类则会跳转到其相应的界面。
###开发说明<br>
	使用Android Studio的开发人员可直接在开发工具中打开相应的源码，参考源码进行开发。
	不使用源码则需要在build.gradle文件中添加：compile’com.speedata:r6:1.3’
    使用Eclipse的开发人员，需要把”eclipse源码“文件夹中的两个源码都导入开发工具，其中”r6lib“作为library，被r6程序源码调用。
###API文档<br>
	详细的接口说明在showdoc，地址：https://www.showdoc.cc/11377?page_id=102430

