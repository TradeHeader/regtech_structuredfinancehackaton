package cdm.product.template.meta;

import cdm.product.template.SecurityLeg;
import cdm.product.template.validation.SecurityLegTypeFormatValidator;
import cdm.product.template.validation.SecurityLegValidator;
import cdm.product.template.validation.exists.SecurityLegOnlyExistsValidator;
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
@RosettaMeta(model=SecurityLeg.class)
public class SecurityLegMeta implements RosettaMetaData<SecurityLeg> {

	@Override
	public List<Validator<? super SecurityLeg>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.SecurityLegSecurityLegChoice.class)
		);
	}
	
	@Override
	public List<Function<? super SecurityLeg, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SecurityLeg> validator() {
		return new SecurityLegValidator();
	}

	@Override
	public Validator<? super SecurityLeg> typeFormatValidator() {
		return new SecurityLegTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SecurityLeg, Set<String>> onlyExistsValidator() {
		return new SecurityLegOnlyExistsValidator();
	}
}
