<?xml version = '1.0' encoding = 'ISO-8859-1' ?>
<asm version="1.0" name="0">
	<cp>
		<constant value="LibHelpers"/>
		<constant value="main"/>
		<constant value="A"/>
		<constant value="self"/>
		<constant value="getRmType"/>
		<constant value="MopenEHRMM!ARCHETYPE;"/>
		<constant value="0"/>
		<constant value="definition"/>
		<constant value="rmTypeName"/>
		<constant value="6:2-6:6"/>
		<constant value="6:2-6:17"/>
		<constant value="6:2-6:28"/>
		<constant value="getOntName"/>
		<constant value="1"/>
		<constant value="J"/>
		<constant value="2"/>
		<constant value="at"/>
		<constant value="J.startsWith(J):J"/>
		<constant value="6"/>
		<constant value=""/>
		<constant value="57"/>
		<constant value="Sequence"/>
		<constant value="#native"/>
		<constant value="ontology"/>
		<constant value="termDefinitions"/>
		<constant value="3"/>
		<constant value="language"/>
		<constant value="en"/>
		<constant value="J.=(J):J"/>
		<constant value="B.not():B"/>
		<constant value="28"/>
		<constant value="CJ.including(J):CJ"/>
		<constant value="J.first():J"/>
		<constant value="items"/>
		<constant value="code"/>
		<constant value="41"/>
		<constant value="id"/>
		<constant value="text"/>
		<constant value="54"/>
		<constant value="value"/>
		<constant value="34:5-34:7"/>
		<constant value="34:19-34:23"/>
		<constant value="34:5-34:24"/>
		<constant value="41:4-41:6"/>
		<constant value="36:4-36:7"/>
		<constant value="36:4-36:16"/>
		<constant value="36:4-36:32"/>
		<constant value="36:43-36:44"/>
		<constant value="36:43-36:53"/>
		<constant value="36:56-36:60"/>
		<constant value="36:43-36:60"/>
		<constant value="36:4-36:61"/>
		<constant value="36:4-36:70"/>
		<constant value="36:4-36:76"/>
		<constant value="37:27-37:28"/>
		<constant value="37:27-37:33"/>
		<constant value="37:35-37:37"/>
		<constant value="37:27-37:37"/>
		<constant value="36:4-37:38"/>
		<constant value="36:4-37:47"/>
		<constant value="36:4-37:53"/>
		<constant value="38:26-38:27"/>
		<constant value="38:26-38:30"/>
		<constant value="38:32-38:38"/>
		<constant value="38:26-38:38"/>
		<constant value="36:4-38:39"/>
		<constant value="36:4-38:48"/>
		<constant value="36:4-38:54"/>
		<constant value="34:2-43:7"/>
		<constant value="x"/>
		<constant value="y"/>
		<constant value="z"/>
		<constant value="obj"/>
	</cp>
	<operation name="1">
		<context type="2"/>
		<parameters>
		</parameters>
		<code>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="3" begin="0" end="-1"/>
		</localvariabletable>
	</operation>
	<operation name="4">
		<context type="5"/>
		<parameters>
		</parameters>
		<code>
			<load arg="6"/>
			<get arg="7"/>
			<get arg="8"/>
		</code>
		<linenumbertable>
			<lne id="9" begin="0" end="0"/>
			<lne id="10" begin="0" end="1"/>
			<lne id="11" begin="0" end="2"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="3" begin="0" end="2"/>
		</localvariabletable>
	</operation>
	<operation name="12">
		<context type="2"/>
		<parameters>
			<parameter name="13" type="14"/>
			<parameter name="15" type="14"/>
		</parameters>
		<code>
			<load arg="13"/>
			<push arg="16"/>
			<call arg="17"/>
			<if arg="18"/>
			<push arg="19"/>
			<goto arg="20"/>
			<push arg="21"/>
			<push arg="22"/>
			<new/>
			<push arg="21"/>
			<push arg="22"/>
			<new/>
			<push arg="21"/>
			<push arg="22"/>
			<new/>
			<load arg="15"/>
			<get arg="23"/>
			<get arg="24"/>
			<iterate/>
			<store arg="25"/>
			<load arg="25"/>
			<get arg="26"/>
			<push arg="27"/>
			<call arg="28"/>
			<call arg="29"/>
			<if arg="30"/>
			<load arg="25"/>
			<call arg="31"/>
			<enditerate/>
			<call arg="32"/>
			<get arg="33"/>
			<iterate/>
			<store arg="25"/>
			<load arg="25"/>
			<get arg="34"/>
			<load arg="13"/>
			<call arg="28"/>
			<call arg="29"/>
			<if arg="35"/>
			<load arg="25"/>
			<call arg="31"/>
			<enditerate/>
			<call arg="32"/>
			<get arg="33"/>
			<iterate/>
			<store arg="25"/>
			<load arg="25"/>
			<get arg="36"/>
			<push arg="37"/>
			<call arg="28"/>
			<call arg="29"/>
			<if arg="38"/>
			<load arg="25"/>
			<call arg="31"/>
			<enditerate/>
			<call arg="32"/>
			<get arg="39"/>
		</code>
		<linenumbertable>
			<lne id="40" begin="0" end="0"/>
			<lne id="41" begin="1" end="1"/>
			<lne id="42" begin="0" end="2"/>
			<lne id="43" begin="4" end="4"/>
			<lne id="44" begin="15" end="15"/>
			<lne id="45" begin="15" end="16"/>
			<lne id="46" begin="15" end="17"/>
			<lne id="47" begin="20" end="20"/>
			<lne id="48" begin="20" end="21"/>
			<lne id="49" begin="22" end="22"/>
			<lne id="50" begin="20" end="23"/>
			<lne id="51" begin="12" end="28"/>
			<lne id="52" begin="12" end="29"/>
			<lne id="53" begin="12" end="30"/>
			<lne id="54" begin="33" end="33"/>
			<lne id="55" begin="33" end="34"/>
			<lne id="56" begin="35" end="35"/>
			<lne id="57" begin="33" end="36"/>
			<lne id="58" begin="9" end="41"/>
			<lne id="59" begin="9" end="42"/>
			<lne id="60" begin="9" end="43"/>
			<lne id="61" begin="46" end="46"/>
			<lne id="62" begin="46" end="47"/>
			<lne id="63" begin="48" end="48"/>
			<lne id="64" begin="46" end="49"/>
			<lne id="65" begin="6" end="54"/>
			<lne id="66" begin="6" end="55"/>
			<lne id="67" begin="6" end="56"/>
			<lne id="68" begin="0" end="56"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="3" name="69" begin="19" end="27"/>
			<lve slot="3" name="70" begin="32" end="40"/>
			<lve slot="3" name="71" begin="45" end="53"/>
			<lve slot="0" name="3" begin="0" end="56"/>
			<lve slot="1" name="36" begin="0" end="56"/>
			<lve slot="2" name="72" begin="0" end="56"/>
		</localvariabletable>
	</operation>
</asm>
