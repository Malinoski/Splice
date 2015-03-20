<?xml version="1.0" encoding="UTF-8"?>
<!-- Edited by XMLSpy® -->
<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  
xmlns:xhtml="http://www.w3.org/1999/xhtml" 
xmlns:path="br.martin.splice.PathGetter" 
xmlns:saxon="http://icl.com/saxon" 
exclude-result-prefixes="xhtml xsl xs path saxon">

<xsl:template match="/" name="getter" xmlns:path="br.martin.splice.PathGetter">
  <xsl:variable name="pathInstance" select="path:new()" />
  <xsl:variable name="archId" select="'openEHR-EHR-OBSERVATION.blood_pressure.v1'" />
  <xsl:variable name="nodeId" select="'at0004'" />
  <html>
  <head>
  	<title><xsl:value-of select="xhtml:html/xhtml:head/xhtml:title" /></title>
  </head>
  <body>
    <h2>EHR</h2>
    <xsl:for-each select="xhtml:html/xhtml:body/xhtml:div/xhtml:div/xhtml:div/xhtml:div">
    	<id><xsl:value-of select="path:getArchetypePath($pathInstance,$archId,$nodeId)" /></id>
    </xsl:for-each>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>