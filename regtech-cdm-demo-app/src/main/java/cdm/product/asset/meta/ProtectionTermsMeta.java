package cdm.product.asset.meta;

import cdm.product.asset.ProtectionTerms;
import cdm.product.asset.validation.ProtectionTermsTypeFormatValidator;
import cdm.product.asset.validation.ProtectionTermsValidator;
import cdm.product.asset.validation.exists.ProtectionTermsOnlyExistsValidator;
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
@RosettaMeta(model=ProtectionTerms.class)
public class ProtectionTermsMeta implements RosettaMetaData<ProtectionTerms> {

	@Override
	public List<Validator<? super ProtectionTerms>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ProtectionTerms, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ProtectionTerms> validator() {
		return new ProtectionTermsValidator();
	}

	@Override
	public Validator<? super ProtectionTerms> typeFormatValidator() {
		return new ProtectionTermsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ProtectionTerms, Set<String>> onlyExistsValidator() {
		return new ProtectionTermsOnlyExistsValidator();
	}
}
