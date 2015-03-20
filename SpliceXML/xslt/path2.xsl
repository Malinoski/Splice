<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  
xmlns:xhtml="http://www.w3.org/1999/xhtml" 
xmlns:path="br.martin.splice.PathGetter" 
xmlns:saxon="http://icl.com/saxon" 
exclude-result-prefixes="xhtml xsl xs path saxon">

	<xsl:template match="*">
		<xsl:comment>
			<xsl:value-of select="name()"/> 
			<xsl:apply-templates select="@*"/>
		</xsl:comment> 
		<xsl:apply-templates />
	</xsl:template>
	
	<xsl:template match="@*">
		<xsl:text>||</xsl:text>
		<xsl:value-of select="name()"/>
		<xsl:apply-templates />
	</xsl:template>
	
	<xsl:template match=""></xsl:template>
	
</xsl:stylesheet>