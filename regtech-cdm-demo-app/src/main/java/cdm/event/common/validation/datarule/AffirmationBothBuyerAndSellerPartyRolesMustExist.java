package cdm.event.common.validation.datarule;

import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.PartyRoleEnum;
import cdm.event.common.Affirmation;
import cdm.event.common.Lineage;
import cdm.event.common.Trade;
import cdm.event.common.metafields.ReferenceWithMetaTrade;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("AffirmationBothBuyerAndSellerPartyRolesMustExist")
@ImplementedBy(AffirmationBothBuyerAndSellerPartyRolesMustExist.Default.class)
public interface AffirmationBothBuyerAndSellerPartyRolesMustExist extends Validator<Affirmation> {
	
	String NAME = "AffirmationBothBuyerAndSellerPartyRolesMustExist";
	String DEFINITION = "if lineage -> tradeReference -> tradableProduct -> product -> security exists then partyRole -> role contains PartyRoleEnum -> Buyer or partyRole -> role contains PartyRoleEnum -> Seller";
	
	ValidationResult<Affirmation> validate(RosettaPath path, Affirmation affirmation);
	
	class Default implements AffirmationBothBuyerAndSellerPartyRolesMustExist {
	
		@Override
		public ValidationResult<Affirmation> validate(RosettaPath path, Affirmation affirmation) {
			ComparisonResult result = executeDataRule(affirmation);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Affirmation", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Affirmation", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Affirmation affirmation) {
			try {
				if (exists(MapperS.of(affirmation).<Lineage>map("getLineage", _affirmation -> _affirmation.getLineage()).<ReferenceWithMetaTrade>mapC("getTradeReference", lineage -> lineage.getTradeReference()).<Trade>map("getValue", _f->_f.getValue()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<Security>map("getSecurity", product -> product.getSecurity())).getOrDefault(false)) {
					return contains(MapperS.of(affirmation).<PartyRole>mapC("getPartyRole", _affirmation -> _affirmation.getPartyRole()).<PartyRoleEnum>map("getRole", partyRole -> partyRole.getRole()), MapperS.of(PartyRoleEnum.BUYER)).or(contains(MapperS.of(affirmation).<PartyRole>mapC("getPartyRole", _affirmation -> _affirmation.getPartyRole()).<PartyRoleEnum>map("getRole", partyRole -> partyRole.getRole()), MapperS.of(PartyRoleEnum.SELLER)));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AffirmationBothBuyerAndSellerPartyRolesMustExist {
	
		@Override
		public ValidationResult<Affirmation> validate(RosettaPath path, Affirmation affirmation) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Affirmation", path, DEFINITION);
		}
	}
}
