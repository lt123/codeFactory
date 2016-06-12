package ${basePackage}.${typeModel};
<#if tableModel.flag>

import java.util.Date;
</#if>

/**
 * ${tableModel.tabDesc}
 * @author: ${author}
 * @date: ${createDate}
 * create by codeFactory
 */
public class ${tableModel.tabName} {
	<#list tableModel.colName as col>
	<#if tableModel.colDesc[col_index]!="">
	/**
	 * ${tableModel.colDesc[col_index]}
	 */
	</#if>
	public ${tableModel.colType[col_index]} ${col};
	
	</#list>
	
	<#list tableModel.colName as col>
	public ${tableModel.colType[col_index]} get${col?cap_first}() {
		return ${col};
	}

	public void set${col?cap_first}(${tableModel.colType[col_index]} ${col}) {
		this.${col} = ${col};
	}
	</#list>
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("${tableModel.tabName} [");
		<#list tableModel.colName as col>
			sb.append("${col}=" + ${col} + ",");
		</#list>
		sb.replace(sb.length()-1, sb.length(), " ]");
		return sb.toString();
	}
	
	
}
