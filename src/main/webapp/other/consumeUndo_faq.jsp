<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
</br><b>消费撤销交易您可能会遇到...</b>
<ul style="font-size: 10px;font-weight: normal;">
<li>
  原交易状态不正确[2050001]:撤销类交易的交易金额和币种与原交易不匹配
</li>
<br>
<li>
   原交易状态不正确[2050002]:撤销原交易状态错,可能已经做过撤销或退货了
</li>
<br>
<li>
  原交易状态不正确[2040004]:撤销交易报文origQryId上送错误，没有找到被撤销的交易
</li>
</ul>
<jsp:include  page="/other/more_faq.jsp"/>