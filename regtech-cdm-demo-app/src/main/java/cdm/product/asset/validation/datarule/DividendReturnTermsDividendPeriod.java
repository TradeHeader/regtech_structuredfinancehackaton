package cdm.product.asset.validation.datarule;

import cdm.product.asset.DividendPaymentDate;
import cdm.product.asset.DividendPeriod;
import cdm.product.asset.DividendPeriodEnum;
import cdm.product.asset.DividendReturnTerms;
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
@RosettaDataRule("DividendReturnTermsDividendPeriod")
@ImplementedBy(DividendReturnTermsDividendPeriod.Default.class)
public interface DividendReturnTermsDividendPeriod extends Validator<DividendReturnTerms> {
	
	String NAME = "DividendReturnTermsDividendPeriod";
	String DEFINITION = "if firstOrSecondPeriod exists then dividendPeriod -> startDate is absent and dividendPeriod -> endDate is absent";
	
	ValidationResult<DividendReturnTerms> validate(RosettaPath path, DividendReturnTerms dividendReturnTerms);
	
	class Default implements DividendReturnTermsDividendPeriod {
	
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
				if (exists(MapperS.of(dividendReturnTerms).<DividendPeriodEnum>map("getFirstOrSecondPeriod", _dividendReturnTerms -> _dividendReturnTerms.getFirstOrSecondPeriod())).getOrDefault(false)) {
					return notExists(MapperS.of(dividendReturnTerms).<DividendPeriod>mapC("getDividendPeriod", _dividendReturnTerms -> _dividendReturnTerms.getDividendPeriod()).<DividendPaymentDate>map("getStartDate", dividendPeriod -> dividendPeriod.getStartDate())).and(notExists(MapperS.of(dividendReturnTerms).<DividendPeriod>mapC("getDividendPeriod", _dividendReturnTerms -> _dividendReturnTerms.getDividendPeriod()).<DividendPaymentDate>map("getEndDate", dividendPeriod -> dividendPeriod.getEndDate())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements DividendReturnTermsDividendPeriod {
	
		@Override
		public ValidationResult<DividendReturnTerms> validate(RosettaPath path, DividendReturnTerms dividendReturnTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DividendReturnTerms", path, DEFINITION);
		}
	}
}
