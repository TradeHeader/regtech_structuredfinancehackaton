package cdm.event.common.validation.datarule;

import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.PartyRoleEnum;
import cdm.event.common.Confirmation;
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
@RosettaDataRule("ConfirmationBothBuyerAndSellerPartyRolesMustExist")
@ImplementedBy(ConfirmationBothBuyerAndSellerPartyRolesMustExist.Default.class)
public interface ConfirmationBothBuyerAndSellerPartyRolesMustExist extends Validator<Confirmation> {
	
	String NAME = "ConfirmationBothBuyerAndSellerPartyRolesMustExist";
	String DEFINITION = "if lineage -> tradeReference -> tradableProduct -> product -> security exists then partyRole -> role contains PartyRoleEnum -> Buyer or partyRole -> role contains PartyRoleEnum -> Seller";
	
	ValidationResult<Confirmation> validate(RosettaPath path, Confirmation confirmation);
	
	class Default implements ConfirmationBothBuyerAndSellerPartyRolesMustExist {
	
		@Override
		public ValidationResult<Confirmation> validate(RosettaPath path, Confirmation confirmation) {
			ComparisonResult result = executeDataRule(confirmation);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Confirmation", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Confirmation", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Confirmation confirmation) {
			try {
				if (exists(MapperS.of(confirmation).<Lineage>map("getLineage", _confirmation -> _confirmation.getLineage()).<ReferenceWithMetaTrade>mapC("getTradeReference", lineage -> lineage.getTradeReference()).<Trade>map("getValue", _f->_f.getValue()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<Security>map("getSecurity", product -> product.getSecurity())).getOrDefault(false)) {
					return contains(MapperS.of(confirmation).<PartyRole>mapC("getPartyRole", _confirmation -> _confirmation.getPartyRole()).<PartyRoleEnum>map("getRole", partyRole -> partyRole.getRole()), MapperS.of(PartyRoleEnum.BUYER)).or(contains(MapperS.of(confirmation).<PartyRole>mapC("getPartyRole", _confirmation -> _confirmation.getPartyRole()).<PartyRoleEnum>map("getRole", partyRole -> partyRole.getRole()), MapperS.of(PartyRoleEnum.SELLER)));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ConfirmationBothBuyerAndSellerPartyRolesMustExist {
	
		@Override
		public ValidationResult<Confirmation> validate(RosettaPath path, Confirmation confirmation) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Confirmation", path, DEFINITION);
		}
	}
}
