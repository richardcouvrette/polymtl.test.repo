<#include "../init.ftl">

<#assign fileEntryTitle = "">
<#assign fileEntryURL = "">

<#if (fields??) && (fieldValue != "")>
	<#assign fileJSONObject = getFileJSONObject(fieldRawValue)>

	<#assign fileEntry = getFileEntry(fileJSONObject)>

	<#if (fileEntry != "")>
		<#assign fileEntryTitle = fileEntry.getTitle()>
		<#assign fileEntryURL = getFileEntryURL(fileEntry)>
	</#if>
</#if>

<@aui["field-wrapper"] label=label>
	<a href="${fileEntryURL}">${fileEntryTitle}</a>
</@>