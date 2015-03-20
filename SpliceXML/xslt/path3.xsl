<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*"/>
			<xsl:apply-templates />
		</xsl:copy>
	</xsl:template>
	
	<xsl:template match="*[@name]">
		<xsl:choose>
			<xsl:when test="name()='input'">
				<xsl:variable name="inputPath" select="'Aqui vai o path do nó!!!'"></xsl:variable>
				<xsl:element name="input">
					<xsl:attribute name="type">
						<xsl:value-of select="@type" />
					</xsl:attribute>
					<xsl:attribute name="id">
						<xsl:value-of select="@id" />
					</xsl:attribute>
					<xsl:attribute name="class">
						<xsl:value-of select="@class" />
					</xsl:attribute>
					<xsl:attribute name="name">
						<xsl:value-of select="$inputPath"/>
					</xsl:attribute>
					<xsl:attribute name="value">
						<xsl:value-of select="@value" />
					</xsl:attribute>
				</xsl:element>
			</xsl:when>
			<xsl:when test="name()='textarea'">
				<xsl:variable name="textPath" select="'Aqui vai o path do nó!!!'"></xsl:variable>
				<xsl:element name="textarea">
					<xsl:attribute name="id">
						<xsl:value-of select="@id" />
					</xsl:attribute>
					<xsl:attribute name="class">
						<xsl:value-of select="@class" />
					</xsl:attribute>
					<xsl:attribute name="name">
						<xsl:value-of select="$textPath"/>
					</xsl:attribute>
					<xsl:attribute name="cols">
						<xsl:value-of select="@cols" />
					</xsl:attribute>
				</xsl:element>
			</xsl:when>
			<xsl:otherwise>
				<xsl:copy><xsl:apply-templates select="@*"/></xsl:copy>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>

<!-- <textarea id="" class="" name="" cols="45"></textarea> -->