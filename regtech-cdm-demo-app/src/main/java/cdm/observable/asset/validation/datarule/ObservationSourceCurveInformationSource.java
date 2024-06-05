package cdm.observable.asset.validation.datarule;

import cdm.observable.asset.Curve;
import cdm.observable.asset.InformationSource;
import cdm.observable.asset.ObservationSource;
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
@RosettaDataRule("ObservationSourceCurveInformationSource")
@ImplementedBy(ObservationSourceCurveInformationSource.Default.class)
public interface ObservationSourceCurveInformationSource extends Validator<ObservationSource> {
	
	String NAME = "ObservationSourceCurveInformationSource";
	String DEFINITION = "(curve exists and informationSource exists) or curve exists or informationSource exists";
	
	ValidationResult<ObservationSource> validate(RosettaPath path, ObservationSource observationSource);
	
	class Default implements ObservationSourceCurveInformationSource {
	
		@Override
		public ValidationResult<ObservationSource> validate(RosettaPath path, ObservationSource observationSource) {
			ComparisonResult result = executeDataRule(observationSource);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ObservationSource", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ObservationSource", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ObservationSource observationSource) {
			try {
				return exists(MapperS.of(observationSource).<Curve>map("getCurve", _observationSource -> _observationSource.getCurve())).and(exists(MapperS.of(observationSource).<InformationSource>map("getInformationSource", _observationSource -> _observationSource.getInformationSource()))).or(exists(MapperS.of(observationSource).<Curve>map("getCurve", _observationSource -> _observationSource.getCurve()))).or(exists(MapperS.of(observationSource).<InformationSource>map("getInformationSource", _observationSource -> _observationSource.getInformationSource())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ObservationSourceCurveInformationSource {
	
		@Override
		public ValidationResult<ObservationSource> validate(RosettaPath path, ObservationSource observationSource) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ObservationSource", path, DEFINITION);
		}
	}
}
