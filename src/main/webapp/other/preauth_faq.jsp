<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
</br><b>跳转网关页面您可能会遇到...</b>
<ul style="font-size: 10px;font-weight: normal;">
<li>
 【wap/PC网关】打开页面出现"此网站的安全证书有问题"或"您的连接不是私密连接"等阻止打开支付页面跳转的内容: 测试环境的证书不被信任，生产没有这个问题，可以点击继续打开
</li>
</br>
<li>
【wap/PC网关】 测试环境跳转报http501错误：测试环境证书不信任，部分浏览器点击信任/继续后会改为get方式访问导致501，这个时候请再回到原生成订单的html重新跳转一下就能正常跳转了，生产无此问题
</li>
</br>

<li>
  【wap/PC网关】 交易失败 11[9100004]Signature verification failed：检查使用的商户号是否是自己使用的，使用的签名证书是否与您发交易的银联环境匹配。
</li>
</br>
<li>
【wap/PC网关】 测试环境测试支付请使用测试卡号测试： 请在FAQ<a href="https://open.unionpay.com/ajweb/help/faq/list" target="_blank"> https://open.unionpay.com/ajweb/help/faq/list</a>搜索“测试卡号”,支付前先点击“免费获取” 短信验证码PC固定填111111，WAP固定填123456
</li>
</br>

<li>
【wap】 测试环境因SSL证书不受信任的原因不支持微信等嵌入式浏览器打开测试，生产无此问题
</li>
</br>
<li>
【wap/PC网关】 跳转银联页面提示5131008:没有开通交易权限，请联系银联业务运营开通权限
</li>
</ul>
<jsp:include  page="/other/more_faq.jsp"/>
					