package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.ObservationDate;
import cdm.product.common.schedule.validation.ObservationDateTypeFormatValidator;
import cdm.product.common.schedule.validation.ObservationDateValidator;
import cdm.product.common.schedule.validation.exists.ObservationDateOnlyExistsValidator;
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
@RosettaMeta(model=ObservationDate.class)
public class ObservationDateMeta implements RosettaMetaData<ObservationDate> {

	@Override
	public List<Validator<? super ObservationDate>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.schedule.validation.datarule.ObservationDateObservationDate.class)
		);
	}
	
	@Override
	public List<Function<? super ObservationDate, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ObservationDate> validator() {
		return new ObservationDateValidator();
	}

	@Override
	public Validator<? super ObservationDate> typeFormatValidator() {
		return new ObservationDateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ObservationDate, Set<String>> onlyExistsValidator() {
		return new ObservationDateOnlyExistsValidator();
	}
}
