<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<hr style="height: 1px; border: none; border-top: 1px dashed #0066CC;" />
</br>
<b>预授权完成交易您可能会遇到...</b>
<ul style="font-size: 10px; font-weight: normal;">
	<li>原交易状态不正确[2040004]:撤销交易报文origQryId上送错误，没有找到被撤销的交易</li>
	</br>
	<li>原交易状态不正确[2050007]:原交易状态错,当前交易未处在预授权状态</li>
	</br>
	<li>与原交易信息不符[2050008]:预授权完成的金额超115%</li>
</ul>
<jsp:include page="/other/more_faq.jsp" />