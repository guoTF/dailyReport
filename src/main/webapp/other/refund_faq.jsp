<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
</br><b>退货交易您可能会遇到...</b>
<ul style="font-size: 10px;font-weight: normal;">

<li>
  原交易状态不正确[2040004]：1)origQryId送错  2)merId与原交易不同  3）原交易状态不正确，比如对已经做过消费撤销的交易做退货
</li>
</br>
<li>
  与原交易信息不符[2050004]：检查下原消费是否已经做过消费撤销或者退货，如果做过部分退货，加上此次的金额是否已经超过原消费
</li>
</br>
</ul>
<jsp:include  page="/other/more_faq.jsp"/>