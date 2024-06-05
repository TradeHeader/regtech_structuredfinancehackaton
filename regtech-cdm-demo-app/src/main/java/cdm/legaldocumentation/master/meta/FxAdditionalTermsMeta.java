package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.FxAdditionalTerms;
import cdm.legaldocumentation.master.validation.FxAdditionalTermsTypeFormatValidator;
import cdm.legaldocumentation.master.validation.FxAdditionalTermsValidator;
import cdm.legaldocumentation.master.validation.exists.FxAdditionalTermsOnlyExistsValidator;
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
@RosettaMeta(model=FxAdditionalTerms.class)
public class FxAdditionalTermsMeta implements RosettaMetaData<FxAdditionalTerms> {

	@Override
	public List<Validator<? super FxAdditionalTerms>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FxAdditionalTerms, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FxAdditionalTerms> validator() {
		return new FxAdditionalTermsValidator();
	}

	@Override
	public Validator<? super FxAdditionalTerms> typeFormatValidator() {
		return new FxAdditionalTermsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FxAdditionalTerms, Set<String>> onlyExistsValidator() {
		return new FxAdditionalTermsOnlyExistsValidator();
	}
}
