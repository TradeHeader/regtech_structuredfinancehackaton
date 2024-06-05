package cdm.observable.event.meta;

import cdm.observable.event.FeaturePayment;
import cdm.observable.event.validation.FeaturePaymentTypeFormatValidator;
import cdm.observable.event.validation.FeaturePaymentValidator;
import cdm.observable.event.validation.exists.FeaturePaymentOnlyExistsValidator;
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
@RosettaMeta(model=FeaturePayment.class)
public class FeaturePaymentMeta implements RosettaMetaData<FeaturePayment> {

	@Override
	public List<Validator<? super FeaturePayment>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.event.validation.datarule.FeaturePaymentFeaturePaymentChoice.class),
			factory.create(cdm.observable.event.validation.datarule.FeaturePaymentAmount.class)
		);
	}
	
	@Override
	public List<Function<? super FeaturePayment, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FeaturePayment> validator() {
		return new FeaturePaymentValidator();
	}

	@Override
	public Validator<? super FeaturePayment> typeFormatValidator() {
		return new FeaturePaymentTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FeaturePayment, Set<String>> onlyExistsValidator() {
		return new FeaturePaymentOnlyExistsValidator();
	}
}
