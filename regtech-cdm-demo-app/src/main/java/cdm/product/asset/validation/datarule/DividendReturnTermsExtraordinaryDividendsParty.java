package cdm.product.asset.validation.datarule;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.asset.DividendReturnTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
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
@RosettaDataRule("DividendReturnTermsExtraordinaryDividendsParty")
@ImplementedBy(DividendReturnTermsExtraordinaryDividendsParty.Default.class)
public interface DividendReturnTermsExtraordinaryDividendsParty extends Validator<DividendReturnTerms> {
	
	String NAME = "DividendReturnTermsExtraordinaryDividendsParty";
	String DEFINITION = "if extraordinaryDividendsParty exists then extraordinaryDividendsParty = AncillaryRoleEnum -> ExtraordinaryDividendsParty";
	
	ValidationResult<DividendReturnTerms> validate(RosettaPath path, DividendReturnTerms dividendReturnTerms);
	
	class Default implements DividendReturnTermsExtraordinaryDividendsParty {
	
		@Override
		public ValidationResult<DividendReturnTerms> validate(RosettaPath path, DividendReturnTerms dividendReturnTerms) {
			ComparisonResult result = executeDataRule(dividendReturnTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DividendReturnTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "DividendReturnTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(DividendReturnTerms dividendReturnTerms) {
			try {
				if (exists(MapperS.of(dividendReturnTerms).<AncillaryRoleEnum>map("getExtraordinaryDividendsParty", _dividendReturnTerms -> _dividendReturnTerms.getExtraordinaryDividendsParty())).getOrDefault(false)) {
					return areEqual(MapperS.of(dividendReturnTerms).<AncillaryRoleEnum>map("getExtraordinaryDividendsParty", _dividendReturnTerms -> _dividendReturnTerms.getExtraordinaryDividendsParty()), MapperS.of(AncillaryRoleEnum.EXTRAORDINARY_DIVIDENDS_PARTY), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements DividendReturnTermsExtraordinaryDividendsParty {
	
		@Override
		public ValidationResult<DividendReturnTerms> validate(RosettaPath path, DividendReturnTerms dividendReturnTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DividendReturnTerms", path, DEFINITION);
		}
	}
}
