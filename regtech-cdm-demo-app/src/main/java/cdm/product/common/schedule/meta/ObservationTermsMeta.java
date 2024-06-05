package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.ObservationTerms;
import cdm.product.common.schedule.validation.ObservationTermsTypeFormatValidator;
import cdm.product.common.schedule.validation.ObservationTermsValidator;
import cdm.product.common.schedule.validation.exists.ObservationTermsOnlyExistsValidator;
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
@RosettaMeta(model=ObservationTerms.class)
public class ObservationTermsMeta implements RosettaMetaData<ObservationTerms> {

	@Override
	public List<Validator<? super ObservationTerms>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.schedule.validation.datarule.ObservationTermsPricingTime.class)
		);
	}
	
	@Override
	public List<Function<? super ObservationTerms, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ObservationTerms> validator() {
		return new ObservationTermsValidator();
	}

	@Override
	public Validator<? super ObservationTerms> typeFormatValidator() {
		return new ObservationTermsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ObservationTerms, Set<String>> onlyExistsValidator() {
		return new ObservationTermsOnlyExistsValidator();
	}
}
