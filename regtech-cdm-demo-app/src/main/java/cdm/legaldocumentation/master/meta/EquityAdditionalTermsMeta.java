package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.EquityAdditionalTerms;
import cdm.legaldocumentation.master.validation.EquityAdditionalTermsTypeFormatValidator;
import cdm.legaldocumentation.master.validation.EquityAdditionalTermsValidator;
import cdm.legaldocumentation.master.validation.exists.EquityAdditionalTermsOnlyExistsValidator;
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
@RosettaMeta(model=EquityAdditionalTerms.class)
public class EquityAdditionalTermsMeta implements RosettaMetaData<EquityAdditionalTerms> {

	@Override
	public List<Validator<? super EquityAdditionalTerms>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super EquityAdditionalTerms, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super EquityAdditionalTerms> validator() {
		return new EquityAdditionalTermsValidator();
	}

	@Override
	public Validator<? super EquityAdditionalTerms> typeFormatValidator() {
		return new EquityAdditionalTermsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super EquityAdditionalTerms, Set<String>> onlyExistsValidator() {
		return new EquityAdditionalTermsOnlyExistsValidator();
	}
}
