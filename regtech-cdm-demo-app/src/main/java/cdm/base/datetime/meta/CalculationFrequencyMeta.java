package cdm.base.datetime.meta;

import cdm.base.datetime.CalculationFrequency;
import cdm.base.datetime.validation.CalculationFrequencyTypeFormatValidator;
import cdm.base.datetime.validation.CalculationFrequencyValidator;
import cdm.base.datetime.validation.exists.CalculationFrequencyOnlyExistsValidator;
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
@RosettaMeta(model=CalculationFrequency.class)
public class CalculationFrequencyMeta implements RosettaMetaData<CalculationFrequency> {

	@Override
	public List<Validator<? super CalculationFrequency>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.datetime.validation.datarule.CalculationFrequencyMoy.class),
			factory.create(cdm.base.datetime.validation.datarule.CalculationFrequencyDom.class),
			factory.create(cdm.base.datetime.validation.datarule.CalculationFrequencyWom.class),
			factory.create(cdm.base.datetime.validation.datarule.CalculationFrequencyMoyTerm.class),
			factory.create(cdm.base.datetime.validation.datarule.CalculationFrequencyDomTerm.class),
			factory.create(cdm.base.datetime.validation.datarule.CalculationFrequencyDowTerm.class),
			factory.create(cdm.base.datetime.validation.datarule.CalculationFrequencyWomTerm.class)
		);
	}
	
	@Override
	public List<Function<? super CalculationFrequency, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CalculationFrequency> validator() {
		return new CalculationFrequencyValidator();
	}

	@Override
	public Validator<? super CalculationFrequency> typeFormatValidator() {
		return new CalculationFrequencyTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CalculationFrequency, Set<String>> onlyExistsValidator() {
		return new CalculationFrequencyOnlyExistsValidator();
	}
}
