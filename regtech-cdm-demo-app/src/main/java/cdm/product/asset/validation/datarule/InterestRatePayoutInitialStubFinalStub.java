package cdm.product.asset.validation.datarule;

import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.StubValue;
import cdm.product.common.schedule.StubPeriod;
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
@RosettaDataRule("InterestRatePayoutInitialStubFinalStub")
@ImplementedBy(InterestRatePayoutInitialStubFinalStub.Default.class)
public interface InterestRatePayoutInitialStubFinalStub extends Validator<InterestRatePayout> {
	
	String NAME = "InterestRatePayoutInitialStubFinalStub";
	String DEFINITION = "if stubPeriod exists then stubPeriod -> initialStub exists or stubPeriod -> finalStub exists";
	
	ValidationResult<InterestRatePayout> validate(RosettaPath path, InterestRatePayout interestRatePayout);
	
	class Default implements InterestRatePayoutInitialStubFinalStub {
	
		@Override
		public ValidationResult<InterestRatePayout> validate(RosettaPath path, InterestRatePayout interestRatePayout) {
			ComparisonResult result = executeDataRule(interestRatePayout);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "InterestRatePayout", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "InterestRatePayout", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(InterestRatePayout interestRatePayout) {
			try {
				if (exists(MapperS.of(interestRatePayout).<StubPeriod>map("getStubPeriod", _interestRatePayout -> _interestRatePayout.getStubPeriod())).getOrDefault(false)) {
					return exists(MapperS.of(interestRatePayout).<StubPeriod>map("getStubPeriod", _interestRatePayout -> _interestRatePayout.getStubPeriod()).<StubValue>map("getInitialStub", stubPeriod -> stubPeriod.getInitialStub())).or(exists(MapperS.of(interestRatePayout).<StubPeriod>map("getStubPeriod", _interestRatePayout -> _interestRatePayout.getStubPeriod()).<StubValue>map("getFinalStub", stubPeriod -> stubPeriod.getFinalStub())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements InterestRatePayoutInitialStubFinalStub {
	
		@Override
		public ValidationResult<InterestRatePayout> validate(RosettaPath path, InterestRatePayout interestRatePayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "InterestRatePayout", path, DEFINITION);
		}
	}
}
