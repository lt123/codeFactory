package com.app.code.model;
<#if flag>

import java.util.Date;
</#if>


=====

<#list passwords as pwd>
	${pwd} >> ${usernames[pwd_index]}
</#list>