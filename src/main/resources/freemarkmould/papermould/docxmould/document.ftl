<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<w:document xmlns:wpc="http://schemas.microsoft.com/office/word/2010/wordprocessingCanvas" xmlns:cx="http://schemas.microsoft.com/office/drawing/2014/chartex" xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships" xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math" xmlns:v="urn:schemas-microsoft-com:vml" xmlns:wp14="http://schemas.microsoft.com/office/word/2010/wordprocessingDrawing" xmlns:wp="http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing" xmlns:w10="urn:schemas-microsoft-com:office:word" xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main" xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml" xmlns:w15="http://schemas.microsoft.com/office/word/2012/wordml" xmlns:w16se="http://schemas.microsoft.com/office/word/2015/wordml/symex" xmlns:wpg="http://schemas.microsoft.com/office/word/2010/wordprocessingGroup" xmlns:wpi="http://schemas.microsoft.com/office/word/2010/wordprocessingInk" xmlns:wne="http://schemas.microsoft.com/office/word/2006/wordml" xmlns:wps="http://schemas.microsoft.com/office/word/2010/wordprocessingShape" mc:Ignorable="w14 w15 w16se wp14">
	<#assign number= ["一","二","三","四","五","六","七","八","九","十","十一","十二","十三","十四","十五","十六","十七","十八","十九","二十"]  />
    <#assign categoryRomeIndex= ["Ⅰ","Ⅱ","Ⅲ","Ⅳ","Ⅴ","Ⅵ","Ⅶ","Ⅷ"]  />
    <w:body>
    
		<w:pict>
				<v:shapetype id="_x0000_t75" coordsize="21600,21600" o:spt="75" o:preferrelative="t" path="m@4@5l@4@11@9@11@9@5xe" filled="f" stroked="f">
					<v:stroke joinstyle="miter"/>
					<v:formulas>
						<v:f eqn="if lineDrawn pixelLineWidth 0"/>
						<v:f eqn="sum @0 1 0"/>
						<v:f eqn="sum 0 0 @1"/>
						<v:f eqn="prod @2 1 2"/>
						<v:f eqn="prod @3 21600 pixelWidth"/>
						<v:f eqn="prod @3 21600 pixelHeight"/>
						<v:f eqn="sum @0 0 1"/>
						<v:f eqn="prod @6 1 2"/>
						<v:f eqn="prod @7 21600 pixelWidth"/>
						<v:f eqn="sum @8 21600 0"/>
						<v:f eqn="prod @7 21600 pixelHeight"/>
						<v:f eqn="sum @10 21600 0"/>
					</v:formulas>
					<v:path o:extrusionok="f" gradientshapeok="t" o:connecttype="rect"/>
					<o:lock v:ext="edit" aspectratio="t"/>
				</v:shapetype>
		</w:pict>
		
		<w:p w:rsidR="005320E8" w:rsidRPr="00AE5FF7" w:rsidRDefault="00DD3048" w:rsidP="00AE5FF7">
			<w:pPr>
				<w:jc w:val="left"/>
				<w:rPr>
					<w:sz w:val="44"/>
					<w:szCs w:val="44"/>
				</w:rPr>
			</w:pPr>
            <#if ((QRCodeId)??)>
                <w:r>
                    <w:rPr>
                        <w:noProof/>
                    </w:rPr>
                    <w:pict>
                        <v:shape id="_x0000_s1028" style="position:absolute;margin-left:379.35pt;margin-top:-4.55pt;width:68.25pt;height:66.75pt;z-index:251659264;mso-position-horizontal-relative:text;mso-position-vertical-relative:text" coordsize="21600,21600" o:spt="100" adj="0,,0" path="" stroked="f">
                            <v:stroke joinstyle="miter"/>
                            <v:imagedata r:id="${QRCodeId}" o:title="2"/>
                            <v:formulas/>
                            <v:path o:connecttype="segments"/>
                        </v:shape>
                    </w:pict>
                </w:r>
            </#if>

		<#if (!((mainTitle.mainTitleExist)??))||mainTitle.mainTitleExist>
			<w:p w:rsidR="00C93DDE" w:rsidRPr="00771D19" w:rsidRDefault="00BF47F0" w:rsidP="00C93DDE">
				<w:pPr>
					<w:rPr>
						<w:rFonts w:eastAsia="黑体"/>
						<w:b/>
						<w:sz w:val="30"/>
						<w:szCs w:val="30"/>
					</w:rPr>
				</w:pPr>
				<w:r w:rsidRPr="00771D19">
					<w:rPr>
						<w:rFonts w:eastAsia="黑体" w:hint="eastAsia"/>
						<w:b/>
						<w:sz w:val="30"/>
						<w:szCs w:val="30"/>
					</w:rPr>
					<w:t>${(mainTitle.mainTitleName)}</w:t>
				</w:r>
			</w:p>
		</#if>
		</w:p>
		
		<#if (!((paperInfoBar.paperInfoBarExist)??))||paperInfoBar.paperInfoBarExist >
			<w:p w:rsidR="004D42A0" w:rsidRDefault="00BF47F0" w:rsidP="009C38D0">
				<w:pPr>
				</w:pPr>
				<w:r>
					<w:rPr>
						<w:rFonts w:hint="eastAsia"/>
					</w:rPr>
					<w:t>${(paperInfoBar.paperInfoBarName)}</w:t>
				</w:r>
			</w:p>
		</#if>
		
		<w:p w:rsidR="005320E8" w:rsidRDefault="00562DA1" w:rsidP="008D3330">
			<w:pPr>
				<w:jc w:val="center"/>
			</w:pPr>
			<w:r w:rsidRPr="000E0EA0">
				<w:rPr>
					<w:rFonts w:hint="eastAsia"/>
				</w:rPr>
				<w:t>-------------------------------------------------------------------------------------------------------------------------------</w:t>
			</w:r>
		</w:p>
		
		<#assign qtCategoryIndex= 0/>
		<#list questionsTypeList as qTList>
			<w:p w:rsidR="004D42A0" w:rsidRDefault="003E556B"/>
				
				<#list qTList.questionsList as qMap>
				<w:p w:rsidR="00A81065" w:rsidRDefault="00BF47F0" w:rsidP="00744A41">
					<w:pPr>
						<w:pStyle w:val="a7"/>
						<w:ind w:firstLineChars="0"/>
						<w:textAlignment w:val="center"/>
						<w:spacing w:line="360" w:lineRule="auto"/>
					</w:pPr>
					<#if ((qMap.questionIndex)??)>
						<#if qMap.questionIndex?number<10>
							<w:r><w:t xml:space="preserve">${(qMap.questionIndex)!""}.  </w:t></w:r>
						<#else>
							<w:r><w:t xml:space="preserve">${(qMap.questionIndex)!""}. </w:t></w:r>
						</#if>
						<#if ((qMap.questionSource)??)&&((questionSourceShow)??)&&questionSourceShow?number!=0>
							<w:r>
								<w:t>${(qMap.questionSource)!""}</w:t>
							</w:r>
						</#if>
					</#if>
					<#if (!((qMap.hasDiv)??))||qMap.hasDiv>
					<#else>
						<w:br/>
					</#if>
					${(qMap.questionContent)!""}
					${(qMap.questionSelection)!""}
                </w:p>
				<#if ((qMap.questionSeparateLine)??)&&qMap.questionSeparateLine?number gt 0>
                   <#list 0..qMap.questionSeparateLine?number as i>
		               <w:p w:rsidR="00A81065" w:rsidRDefault="00BF47F0" w:rsidP="00744A41"></w:p>
                   </#list>
				</#if>
				<#if (!((securityMark)??))||securityMark>
					<#if ((qMap.questionIndex)??)&&(qTList.questionsList?size>(qMap.questionIndex)) >
						<w:p ><w:r><w:br w:type="page" /></w:r></w:p>
					</#if>
				</#if>
				<#if (!((devideFiveLine)??))||devideFiveLine>
					<#if ((qMap.questionIndex)??)&&(qTList.questionsList?size>(qMap.questionIndex)) >
						<w:br/>
						<w:br/>
						<w:br/>
						<w:br/>
						<w:br/>
					</#if>
				</#if>

				
				<#if answerType="2"||answerType="4">
					<#if (!((qMap.qChildQues)??))||(qMap.qChildQues?size < 1) >
						<w:p w:rsidR="00A81065" w:rsidRDefault="00BF47F0">
							<w:pPr>
								<w:textAlignment w:val="center"/>
								<w:spacing w:line="360" w:lineRule="auto"/>
								<w:ind w:leftChars="130" w:left="273"/>
							</w:pPr>
							<w:r w:rsidRPr="00043B54">
								<w:t>【答案】</w:t>
							</w:r>
							${(qMap.questionAnswer)!""}
						</w:p>
					</#if>
					
					<w:p w:rsidR="00A81065" w:rsidRDefault="00BF47F0">
						<w:pPr>
							<w:textAlignment w:val="center"/>
							<w:spacing w:line="360" w:lineRule="auto"/>
							<w:ind w:leftChars="130" w:left="273"/>
						</w:pPr>
						<w:r w:rsidRPr="00043B54">
							<#if ((qMap.qChildQues)??)&&(qMap.qChildQues?size > 0) >
								<w:t>【分析】</w:t>
							<#else>
								<w:t>【解析】</w:t>
							</#if>
						</w:r>
						${(qMap.questionAnswerInfo)!""}
					</w:p>
				</#if>
					
				<#if answerType="4">
					<w:p w:rsidR="00A81065" w:rsidRDefault="00BF47F0">
						<w:pPr>
							<w:textAlignment w:val="center"/>
							<w:spacing w:line="360" w:lineRule="auto"/>
							<w:ind w:leftChars="130" w:left="273"/>
						</w:pPr>
						<w:r w:rsidRPr="00043B54">
							<w:t>【知识点】</w:t>
						</w:r>
						<#if ((qMap.questionLabelList)??)&&(qMap.questionLabelList?size > 0)>
							<#list qMap.questionLabelList as qLabel>
								<#if ((qLabel.importance)??)&&qLabel.importance?number = 1 >
									<w:r w:rsidRPr="00043B54">
										<w:rPr>
											<w:color w:val="0091F9"/>
										</w:rPr>
										<w:t>${qLabel.labelName!""}；</w:t>
									</w:r>
								<#else>
								<w:r w:rsidRPr="00043B54">
									<w:t>${qLabel.labelName!""}；</w:t>
								</w:r>
								</#if>
							</#list>
						</#if>
					</w:p>
						
					<w:p w:rsidR="00A81065" w:rsidRDefault="00BF47F0">
						<w:pPr>
							<w:textAlignment w:val="center"/>
							<w:spacing w:line="360" w:lineRule="auto"/>
							<w:ind w:leftChars="130" w:left="273"/>
						</w:pPr>
						<w:r w:rsidRPr="00043B54">
							<w:t>【难度】</w:t>
						</w:r>
						<w:r w:rsidRPr="00043B54">
							<#if ((qMap.questionDifficulty)??)&&qMap.questionDifficulty?number=1>
								<w:t>容易</w:t>
							<#elseif ((qMap.questionDifficulty)??)&&qMap.questionDifficulty?number=2>
								<w:t>较易</w:t>
							<#elseif ((qMap.questionDifficulty)??)&&qMap.questionDifficulty?number=3>
								<w:t>中等</w:t>
							<#elseif ((qMap.questionDifficulty)??)&&qMap.questionDifficulty?number=4>
								<w:t>较难</w:t>
							<#elseif ((qMap.questionDifficulty)??)&&qMap.questionDifficulty?number=5>
								<w:t>困难</w:t>
							<#else>
								<w:t>未知</w:t>
							</#if>
						</w:r>
					</w:p>
				</#if>
					
				<#if answerType="2"||answerType="4">
					<#if ((qMap.qChildQues)??)&&(qMap.qChildQues?size > 0) >
						<#list qMap.qChildQues as childQueMap>
							<w:p w:rsidR="00A81065" w:rsidRDefault="00BF47F0" w:rsidP="00744A41">
							
								<w:pPr>
									<w:pStyle w:val="a7"/>
									<w:ind w:firstLineChars="0"/>
									<w:textAlignment w:val="center"/>
									<w:spacing w:line="360" w:lineRule="auto"/>
								</w:pPr>
								
								<#if ((childQueMap.questionIndex)??)>
									<#if childQueMap.questionIndex?number<10>
										<w:r><w:t xml:space="preserve">${(childQueMap.questionIndex)!""}.  </w:t></w:r>
									<#else>
										<w:r><w:t xml:space="preserve">${(childQueMap.questionIndex)!""}. </w:t></w:r>
									</#if>
								</#if>
								
								<w:r w:rsidRPr="00043B54">
									<w:t>【答案】</w:t>
								</w:r>
								${(childQueMap.questionAnswer)!""}
								
							</w:p>
						
							<w:p w:rsidR="00A81065" w:rsidRDefault="00BF47F0">
								<w:pPr>
									<w:textAlignment w:val="center"/>
									<w:spacing w:line="360" w:lineRule="auto"/>
									<w:ind w:leftChars="130" w:left="273"/>
								</w:pPr>
								<w:r w:rsidRPr="00043B54">
									<w:t xml:space="preserve">【解析】</w:t>
								</w:r>
								${(childQueMap.questionAnswerInfo)!""}
							</w:p>
						
							<#if answerType="4">
								<w:p w:rsidR="00A81065" w:rsidRDefault="00BF47F0">
									<w:pPr>
										<w:textAlignment w:val="center"/>
										<w:spacing w:line="360" w:lineRule="auto"/>
										<w:ind w:leftChars="130" w:left="273"/>
									</w:pPr>
									<w:r w:rsidRPr="00043B54">
										<w:t xml:space="preserve">【知识点】</w:t>
									</w:r>
									
									<#if ((childQueMap.questionLabelList)??)&&(childQueMap.questionLabelList?size >0)>
										<#list childQueMap.questionLabelList as qLabel>
											<#if ((qLabel.importance)??)&&qLabel.importance?number=1>
												<w:r w:rsidRPr="00043B54">
													<w:rPr>
														<w:color w:val="0091F9"/>
													</w:rPr>
													<w:t>${qLabel.labelName!""}；</w:t>
												</w:r>
											<#else>
												<w:r w:rsidRPr="00043B54">
													<w:t>${qLabel.labelName!""}；</w:t>
												</w:r>
											</#if>
										</#list>
									</#if>
								</w:p>
								
								<w:p w:rsidR="00A81065" w:rsidRDefault="00BF47F0">
									<w:pPr>
										<w:textAlignment w:val="center"/>
										<w:spacing w:line="360" w:lineRule="auto"/>
										<w:ind w:leftChars="130" w:left="273"/>
									</w:pPr>
									<w:r w:rsidRPr="00043B54">
										<w:t xml:space="preserve">【难度】</w:t>
									</w:r>
									<w:r w:rsidRPr="00043B54">
										<#if ((childQueMap.questionDifficulty)??)&&childQueMap.questionDifficulty?number=1>
											<w:t>容易</w:t>
										<#elseif ((childQueMap.questionDifficulty)??)&&childQueMap.questionDifficulty?number=2>
											<w:t>较易</w:t>
										<#elseif ((childQueMap.questionDifficulty)??)&&childQueMap.questionDifficulty?number=3>
											<w:t>中等</w:t>
										<#elseif ((childQueMap.questionDifficulty)??)&&childQueMap.questionDifficulty?number=4>
											<w:t>较难</w:t>
										<#elseif ((childQueMap.questionDifficulty)??)&&childQueMap.questionDifficulty?number=5>
											<w:t>困难</w:t>
										<#else>
											<w:t>未知</w:t>
										</#if>
									</w:r>
								</w:p>
							</#if>
						</#list>
					</#if>
				</#if>
			</#list >
		</#list >
		
		<w:bookmarkStart w:id="0" w:name="_GoBack"/>
		<w:bookmarkEnd w:id="0"/>
		
		<#if answerType="1">
			<w:pPr>
		</#if>
				<w:sectPr w:rsidR="00083677" w:rsidRPr="00D33E91" w:rsidSect="007A55E5">
					<w:headerReference w:type="even" r:id="rId7"/>
					<w:headerReference w:type="default" r:id="rId8"/>
					
					<#if paperSizeType="A4"||paperSizeType="B5">
						<w:footerReference w:type="even" r:id="rId15"/>
						<w:footerReference w:type="default" r:id="rId16"/>
					<#else>
						<w:footerReference w:type="even" r:id="rId9"/>
						<w:footerReference w:type="default" r:id="rId10"/>
					</#if>
					
					<#if paperSizeType="A4">
						<w:pgSz w:w="11907" w:h="16839" w:code="9"/>
						
						<w:pgMar w:top="900" w:bottom="900" w:right="1500" 
							<#if (!((bindingLine)??))||bindingLine>
							w:left="2500" 
							 <#else>
							 w:left="1500"
							 </#if>
							w:header="500" w:footer="500" w:gutter="0"/>
						
						<w:cols w:sep="1" w:space="425"/>
						<w:docGrid w:type="lines" w:linePitch="312"/>
					<#elseif paperSizeType="B4">
						<w:pgSz w:w="20639" w:h="14572" w:orient="landscape" w:code="9"/>
					        
						<w:pgMar w:top="1000" w:bottom="1000"  w:right="1500"
							<#if (!((bindingLine)??))||bindingLine>
							w:left="2500" 
							 <#else>
							 w:left="1500"
							 </#if>
							w:header="851" w:footer="592" w:gutter="0"/>
							
						<w:cols w:num="2" w:sep="1" w:space="425"/>
						<w:docGrid w:type="lines" w:linePitch="312"/>
					<#elseif paperSizeType="B5">
						<w:pgSz w:w="14570" w:h="10318" w:orient="landscape" w:code="13"/>
      
						<w:pgMar w:top="1000"  w:bottom="1000" w:right="1500"
							<#if (!((bindingLine)??))||bindingLine>
							w:left="2500" 
							 <#else>
							 w:left="1500"
							 </#if>
							w:header="851" w:footer="592" w:gutter="0"/>
							
						<w:cols w:sep="1" w:space="425"/>
						<w:docGrid w:type="lines" w:linePitch="312"/>
					<#else>
						<w:pgSz w:w="23814" w:h="16840" w:orient="landscape" w:code="9"/>
						
						<w:pgMar w:top="1134"  w:bottom="1134" w:right="1000"
							<#if (!((bindingLine)??))||bindingLine>
							w:left="2500"
							 <#else>
							 w:left="1000"
							 </#if>
							w:header="851" w:footer="692" w:gutter="0"/>
						  
						<w:cols w:num="2" w:sep="1" w:space="425"/>
						<w:docGrid w:type="lines" w:linePitch="312"/>
					</#if>
					
				</w:sectPr>
				
				<#if answerType="1">
			</w:pPr>
					
			<w:p w:rsidR="005320E8" w:rsidRDefault="005320E8" w:rsidP="00B33EF9"></w:p>
			
			<w:p w:rsidR="002A2386" w:rsidRPr="00AE5FF7" w:rsidRDefault="007B751A" w:rsidP="00AE5FF7">
				<w:pPr>
					<w:jc w:val="center"/>
					<w:rPr>
						<w:b/>
					</w:rPr>
				</w:pPr>
				<w:r w:rsidRPr="00AE5FF7">
					<w:rPr>
						<w:rFonts w:hint="eastAsia"/>
						<w:b/>
					</w:rPr>
					<w:lastRenderedPageBreak/>
					<w:t>参考答案及解析</w:t>
				</w:r>
			</w:p>
			
			<#list questionsTypeList as qTList>
				<w:p w:rsidR="00E51600" w:rsidRDefault="005320E8" w:rsidP="00B33EF9">
					<w:pPr>
						<w:rPr>
							<w:b/>
						</w:rPr>
					</w:pPr>
					<w:r>
						<w:rPr>
							<w:rFonts w:hint="eastAsia"/>
							<w:b/>
						</w:rPr>
						<#if ((qTList.questionSecondType)??)&&(qTList.questionSecondType!="")>
							<w:t>${number[qTList_index]}</w:t>
						<#else>
							<w:t>${number[qTList_index]}</w:t>
						</#if>
					</w:r>
				</w:p>
				
				<#list qTList.questionsList as qMap>
					<w:p w:rsidR="00A81065" w:rsidRDefault="00BF47F0" w:rsidP="00744A41">
					<w:pPr>
						<w:pStyle w:val="a7"/>
						<w:ind w:firstLineChars="0"/>
						<w:textAlignment w:val="center"/>
						<w:spacing w:line="360" w:lineRule="auto"/>
					</w:pPr>
					
					<#if ((qMap.questionIndex)??)>
						<#if qMap.questionIndex?number<10>
							<w:r><w:t xml:space="preserve">${(qMap.questionIndex)!""}.  </w:t></w:r>
						<#else>
							<w:r><w:t xml:space="preserve">${(qMap.questionIndex)!""}. </w:t></w:r>
						</#if>
					<#else>
						<w:r w:rsidRPr="00043B54">
							<w:rPr>
								<w:rFonts w:ascii="宋体" w:hAnsi="宋体" w:hint="eastAsia"/>
								<w:sz w:val="10"/>
								<w:szCs w:val="10"/>
							</w:rPr>
							<w:t>●</w:t>
						</w:r>
					</#if>
					
					<#if (!((qMap.qChildQues)??))||(qMap.qChildQues?size < 1) >
						<w:r w:rsidRPr="00043B54">
							<w:t>【答案】</w:t>
						</w:r>
						${(qMap.questionAnswer)!""}
						</w:p>
						<w:p w:rsidR="00A81065" w:rsidRDefault="00BF47F0">
						<w:pPr>
							<w:ind w:leftChars="130" w:left="273"/>
							<w:textAlignment w:val="center"/>
							<w:spacing w:line="360" w:lineRule="auto"/>
						</w:pPr>
					</#if>
					
					<w:r w:rsidRPr="00043B54">
						<#if ((qMap.qChildQues)??)&&(qMap.qChildQues?size > 0)>
							<w:t xml:space="preserve"> 【分析】</w:t>
						<#else>
							<w:t xml:space="preserve"> 【解析】</w:t>
						</#if>
					</w:r>
					
					${(qMap.questionAnswerInfo)!""}
					
					</w:p>
					
					<#if ((qMap.qChildQues)??)&&(qMap.qChildQues?size > 0)>
						<#list qMap.qChildQues as childQueMap>
							<w:p w:rsidR="00A81065" w:rsidRDefault="00BF47F0" w:rsidP="00744A41">
								<w:pPr>
									<w:pStyle w:val="a7"/>
									<w:ind w:firstLineChars="0"/>
									<w:textAlignment w:val="center"/>
									<w:spacing w:line="360" w:lineRule="auto"/>
								</w:pPr>
								<#if ((childQueMap.questionIndex)??)>
									<#if childQueMap.questionIndex?number<10>
										<w:r><w:t xml:space="preserve">${(childQueMap.questionIndex)!""}.  </w:t></w:r>
									<#else>
										<w:r><w:t xml:space="preserve">${(childQueMap.questionIndex)!""}. </w:t></w:r>
									</#if>
								</#if>
								<w:r w:rsidRPr="00043B54">
									<w:t>【答案】</w:t>
								</w:r>
								${(childQueMap.questionAnswer)!""}
							</w:p>
							
							<w:p w:rsidR="00A81065" w:rsidRDefault="00BF47F0">
								<w:pPr>
									<w:textAlignment w:val="center"/>
									<w:spacing w:line="360" w:lineRule="auto"/>
									<w:ind w:leftChars="130" w:left="273"/>
								</w:pPr>
								<w:r w:rsidRPr="00043B54">
									<w:t xml:space="preserve"> 【解析】</w:t>
								</w:r>
								${(childQueMap.questionAnswerInfo)!""}
							</w:p>
						</#list>
					</#if>
					
				</#list>
			</#list>
			
			<w:sectPr w:rsidR="005320E8" w:rsidSect="007A55E5">
			    <w:headerReference w:type="even" r:id="rId13"/>
				<w:headerReference w:type="default" r:id="rId14"/>
				<w:footerReference w:type="even" r:id="rId17"/>
				<w:footerReference w:type="default" r:id="rId18"/>
				<w:pgSz w:w="11906" w:h="16838" w:code="9"/>
				<w:pgMar w:top="1440" w:right="1797" w:bottom="1440" w:left="1797" w:header="851" w:footer="992" w:gutter="0"/>
				<w:pgNumType w:start="1"/>
				<w:cols w:space="425"/>
				<w:docGrid w:type="lines" w:linePitch="312"/>
			</w:sectPr>
		</#if>
		
	</w:body>
</w:document>