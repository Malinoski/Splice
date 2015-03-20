<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:xhtml="http://www.w3.org/1999/xhtml"
xmlns:path="br.martin.splice.PathGetter" 
xmlns:saxon="http://icl.com/saxon">

	<!-- Configura a saída -->
	<xsl:output method="xml" encoding="UTF-8" indent="yes" omit-xml-declaration="yes" />
	
	<!-- Template padrão que copia os elementos/atributos -->
	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*"/>
			<xsl:apply-templates />
		</xsl:copy>
	</xsl:template>  
	
	<!-- Todos os elementos/atributos que não serão copiados devem
	ser tratados por templates específicos  -->
	
	<xsl:template match="*[@name|@title|@href]">
		<xsl:choose>
			<xsl:when test="name()='input'">
				<xsl:variable name="inputArchId" select="normalize-space(ancestor::*[@id='OBSERVATION_data' or @id='INSTRUCTION_activities']/preceding-sibling::*[@id='CARCHETYPEROOT_contentLabelGroup']/child::*[name()='span'])"/>
				<xsl:variable name="inputNodeId" select="normalize-space(ancestor::*[name()='fieldset']/child::*[name()='legend'])" />
				<xsl:variable name="inputPath">
					<xsl:call-template name="getPath">
						<xsl:with-param name="archId" select="$inputArchId" />
						<xsl:with-param name="nodeId" select="$inputNodeId" />
					</xsl:call-template>
				</xsl:variable>
				
				<xsl:element name="{name()}">
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
				<xsl:variable name="textArchId" select="normalize-space(ancestor::*[@id='OBSERVATION_data' or @id='INSTRUCTION_activities']/preceding-sibling::*[@id='CARCHETYPEROOT_contentLabelGroup']/child::*[name()='span'])" />
				<xsl:variable name="textNodeId" select="normalize-space(ancestor::*[name()='fieldset']/child::*[name()='legend'])" />
				<xsl:variable name="textPath">
					<xsl:call-template name="getPath">
						<xsl:with-param name="archId" select="$textArchId" />
						<xsl:with-param name="nodeId" select="$textNodeId" />
					</xsl:call-template>
				</xsl:variable>
				
				<xsl:element name="{name()}">
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
			<xsl:when test="name()='em'">
				<xsl:variable name="emArchId" select="normalize-space(ancestor::*[@id='OBSERVATION_data' or @id='INSTRUCTION_activities']/preceding-sibling::*[@id='CARCHETYPEROOT_contentLabelGroup']/child::*[name()='span'])" />
				<xsl:variable name="emNodeId">
					<xsl:value-of select="@id" />
				</xsl:variable>
				<xsl:variable name="emPath">
					<xsl:call-template name="getPath">
						<xsl:with-param name="archId" select="$emArchId" />
						<xsl:with-param name="nodeId" select="$emNodeId" />
					</xsl:call-template>
				</xsl:variable>
				<xsl:variable name="emMap">
					<xsl:text>${map['</xsl:text>
					<xsl:value-of select="$emPath"/>
					<xsl:text>'][0]}</xsl:text>
				</xsl:variable>				
				
				<xsl:element name="{name()}">
					<xsl:attribute name="id">
						<xsl:value-of select="@id"/>
					</xsl:attribute>
					<xsl:attribute name="class">
						<xsl:value-of select="@class"/>
					</xsl:attribute>
					<xsl:attribute name="title">
						<xsl:value-of select="@title" />
					</xsl:attribute>
					
					<xsl:text><xsl:value-of select="$emMap"/></xsl:text>
				</xsl:element>
			</xsl:when>
			<xsl:when test="name()='a'">
				<xsl:call-template name="aHandler" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:copy>
					<xsl:apply-templates select="node()|@*"/>
				</xsl:copy>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="aHandler">
		<xsl:choose>
			<xsl:when test="@id='at0000'">
				<xsl:copy>
					<xsl:apply-templates select="node()|@*"/>
				</xsl:copy>
			</xsl:when>
			<xsl:when test="current()='download'">
				<xsl:variable name="downArchId" select="normalize-space(ancestor::*[@id='OBSERVATION_data' or @id='INSTRUCTION_activities']/preceding-sibling::*[@id='CARCHETYPEROOT_contentLabelGroup']/child::*[name()='span'])" />
				<xsl:variable name="downNodeId">
				 	<xsl:value-of select="substring-before(normalize-space(ancestor::node()[2]), ' ')" />
				</xsl:variable>
				<xsl:variable name="downPath">
					<xsl:call-template name="getPath">
						<xsl:with-param name="archId" select="$downArchId" />
						<xsl:with-param name="nodeId" select="$downNodeId" />
					</xsl:call-template>
				</xsl:variable>
				<xsl:variable name="downMap">
					<xsl:text>FileDownloadHandler?fileName=${map['</xsl:text>
					<xsl:value-of select="$downPath"/>
					<xsl:text>'][0]}</xsl:text>
				</xsl:variable>
				
				<xsl:element name="{name()}">
					<xsl:attribute name="href">
						<xsl:value-of select="$downMap" />
					</xsl:attribute>
					<xsl:attribute name="target">
						<xsl:value-of select="@target" />
					</xsl:attribute>
					
					<xsl:text><xsl:value-of select="current()" /></xsl:text>
				</xsl:element>
			</xsl:when>
			<xsl:otherwise>
				<xsl:variable name="aArchId" select="normalize-space(ancestor::*[@id='OBSERVATION_data' or @id='INSTRUCTION_activities']/preceding-sibling::*[@id='CARCHETYPEROOT_contentLabelGroup']/child::*[name()='span'])" />
				<xsl:variable name="aNodeId">
					<xsl:value-of select="@id" />
				</xsl:variable>
				<xsl:variable name="aPath">
					<xsl:call-template name="getPath">
						<xsl:with-param name="archId" select="$aArchId" />
						<xsl:with-param name="nodeId" select="$aNodeId" />
					</xsl:call-template>
				</xsl:variable>
				<xsl:variable name="aMap">
					<xsl:text>${map['</xsl:text>
					<xsl:value-of select="$aPath"/>
					<xsl:text>'][0]}</xsl:text>
				</xsl:variable>	
				
				<xsl:element name="{name()}">
					<xsl:attribute name="href">
						<xsl:value-of select="@href" />
					</xsl:attribute>
					<xsl:attribute name="id">
						<xsl:value-of select="@id" />
					</xsl:attribute>
					<xsl:attribute name="class">
						<xsl:value-of select="@class"/>
					</xsl:attribute>
					<xsl:attribute name="title">
						<xsl:value-of select="@title" />
					</xsl:attribute>
					
					<xsl:text><xsl:value-of select="$aMap"/></xsl:text>
				</xsl:element>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="getPath" xmlns:path="br.martin.splice.PathGetter">
		<xsl:variable name="pathInstance" select="path:new()" />
		<xsl:param name="archId" />
		<xsl:param name="nodeId" />
		
		<xsl:value-of select="path:getArchetypePath($pathInstance,$archId,$nodeId)" />
	</xsl:template>
	
</xsl:stylesheet>