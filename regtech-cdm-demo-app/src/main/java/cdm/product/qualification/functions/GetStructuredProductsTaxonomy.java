package cdm.product.qualification.functions;

import cdm.event.common.TradeState;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(GetStructuredProductsTaxonomy.GetStructuredProductsTaxonomyDefault.class)
public abstract class GetStructuredProductsTaxonomy implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected Qualify_Credit_IndexTranche_ABX_ABXTranche qualify_Credit_IndexTranche_ABX_ABXTranche;
	@Inject protected Qualify_Credit_IndexTranche_LCDX_LCDXTranche qualify_Credit_IndexTranche_LCDX_LCDXTranche;
	@Inject protected Qualify_Credit_IndexTranche_LCDX_StandardLCDXBulletTranche qualify_Credit_IndexTranche_LCDX_StandardLCDXBulletTranche;
	@Inject protected Qualify_Credit_Index_ABX_ABXHE qualify_Credit_Index_ABX_ABXHE;
	@Inject protected Qualify_Credit_Index_CMBX_CMBX qualify_Credit_Index_CMBX_CMBX;
	@Inject protected Qualify_Credit_Index_IOS_IOS qualify_Credit_Index_IOS_IOS;
	@Inject protected Qualify_Credit_Index_LCDX_LCDX qualify_Credit_Index_LCDX_LCDX;
	@Inject protected Qualify_Credit_Index_LCDX_StandardLCDXBullet qualify_Credit_Index_LCDX_StandardLCDXBullet;
	@Inject protected Qualify_Credit_Index_MBX_MBX qualify_Credit_Index_MBX_MBX;
	@Inject protected Qualify_Credit_Index_PO_PO qualify_Credit_Index_PO_PO;
	@Inject protected Qualify_Credit_Index_PrimeX_PrimeX qualify_Credit_Index_PrimeX_PrimeX;
	@Inject protected Qualify_Credit_Index_TRX_TRX qualify_Credit_Index_TRX_TRX;
	@Inject protected Qualify_Credit_SingleName_ABS_CDSonCDO qualify_Credit_SingleName_ABS_CDSonCDO;
	@Inject protected Qualify_Credit_SingleName_ABS_MBS qualify_Credit_SingleName_ABS_MBS;
	@Inject protected Qualify_Credit_SingleName_Loans_LCDS qualify_Credit_SingleName_Loans_LCDS;
	@Inject protected Qualify_Credit_SingleName_Loans_StandardLCDSBullet qualify_Credit_SingleName_Loans_StandardLCDSBullet;

	/**
	* @param tradeState 
	* @return taxonomy 
	*/
	public String evaluate(TradeState tradeState) {
		String taxonomy = doEvaluate(tradeState);
		
		return taxonomy;
	}

	protected abstract String doEvaluate(TradeState tradeState);

	public static class GetStructuredProductsTaxonomyDefault extends GetStructuredProductsTaxonomy {
		@Override
		protected String doEvaluate(TradeState tradeState) {
			String taxonomy = null;
			return assignOutput(taxonomy, tradeState);
		}
		
		protected String assignOutput(String taxonomy, TradeState tradeState) {
			if (areEqual(MapperS.of(qualify_Credit_SingleName_Loans_LCDS.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:SingleName:Loans:LCDS";
			} else if (areEqual(MapperS.of(qualify_Credit_SingleName_Loans_StandardLCDSBullet.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:SingleName:Loans:StandardLCDSBullet";
			} else if (areEqual(MapperS.of(qualify_Credit_SingleName_ABS_MBS.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:SingleName:ABS:MBS";
			} else if (areEqual(MapperS.of(qualify_Credit_SingleName_ABS_CDSonCDO.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:SingleName:ABS:CDSonCDO";
			} else if (areEqual(MapperS.of(qualify_Credit_Index_ABX_ABXHE.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:Index:ABX:ABXHE";
			} else if (areEqual(MapperS.of(qualify_Credit_Index_CMBX_CMBX.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:Index:CMBX:CMBX";
			} else if (areEqual(MapperS.of(qualify_Credit_Index_IOS_IOS.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:Index:IOS:IOS";
			} else if (areEqual(MapperS.of(qualify_Credit_Index_MBX_MBX.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:Index:MBX:MBX";
			} else if (areEqual(MapperS.of(qualify_Credit_Index_PO_PO.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:Index:PO:PO";
			} else if (areEqual(MapperS.of(qualify_Credit_Index_PrimeX_PrimeX.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:Index:PrimeX:PrimeX";
			} else if (areEqual(MapperS.of(qualify_Credit_Index_LCDX_StandardLCDXBullet.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:Index:LCDX:StandardLCDXBullet";
			} else if (areEqual(MapperS.of(qualify_Credit_Index_LCDX_LCDX.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:Index:LCDX:LCDX";
			} else if (areEqual(MapperS.of(qualify_Credit_Index_TRX_TRX.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:Index:TRX:TRX";
			} else if (areEqual(MapperS.of(qualify_Credit_IndexTranche_ABX_ABXTranche.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:IndexTranche:ABX:ABXTranche";
			} else if (areEqual(MapperS.of(qualify_Credit_IndexTranche_LCDX_LCDXTranche.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:IndexTranche:LCDX:LCDXTranche";
			} else if (areEqual(MapperS.of(qualify_Credit_IndexTranche_LCDX_StandardLCDXBulletTranche.evaluate(tradeState)), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				taxonomy = "Credit:IndexTranche:LCDX:StandardLCDXBulletTranche";
			} else {
				taxonomy = "To Do";
			}
			
			return taxonomy;
		}
	}
}
