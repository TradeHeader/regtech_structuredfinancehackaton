package cdm.observable.asset.meta;

import cdm.observable.asset.Curve;
import cdm.observable.asset.validation.CurveTypeFormatValidator;
import cdm.observable.asset.validation.CurveValidator;
import cdm.observable.asset.validation.exists.CurveOnlyExistsValidator;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=Curve.class)
public class CurveMeta implements RosettaMetaData<Curve> {

	@Override
	public List<Validator<? super Curve>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.asset.validation.datarule.CurveCurve.class)
		);
	}
	
	@Override
	public List<Function<? super Curve, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Curve> validator() {
		return new CurveValidator();
	}

	@Override
	public Validator<? super Curve> typeFormatValidator() {
		return new CurveTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Curve, Set<String>> onlyExistsValidator() {
		return new CurveOnlyExistsValidator();
	}
}
