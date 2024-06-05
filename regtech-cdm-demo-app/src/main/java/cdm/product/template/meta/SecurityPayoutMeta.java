package cdm.product.template.meta;

import cdm.product.template.SecurityPayout;
import cdm.product.template.validation.SecurityPayoutTypeFormatValidator;
import cdm.product.template.validation.SecurityPayoutValidator;
import cdm.product.template.validation.exists.SecurityPayoutOnlyExistsValidator;
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
@RosettaMeta(model=SecurityPayout.class)
public class SecurityPayoutMeta implements RosettaMetaData<SecurityPayout> {

	@Override
	public List<Validator<? super SecurityPayout>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super SecurityPayout, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SecurityPayout> validator() {
		return new SecurityPayoutValidator();
	}

	@Override
	public Validator<? super SecurityPayout> typeFormatValidator() {
		return new SecurityPayoutTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SecurityPayout, Set<String>> onlyExistsValidator() {
		return new SecurityPayoutOnlyExistsValidator();
	}
}
