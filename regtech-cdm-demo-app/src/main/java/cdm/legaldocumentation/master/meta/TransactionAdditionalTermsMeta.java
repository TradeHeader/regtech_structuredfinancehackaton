package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.TransactionAdditionalTerms;
import cdm.legaldocumentation.master.validation.TransactionAdditionalTermsTypeFormatValidator;
import cdm.legaldocumentation.master.validation.TransactionAdditionalTermsValidator;
import cdm.legaldocumentation.master.validation.exists.TransactionAdditionalTermsOnlyExistsValidator;
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
@RosettaMeta(model=TransactionAdditionalTerms.class)
public class TransactionAdditionalTermsMeta implements RosettaMetaData<TransactionAdditionalTerms> {

	@Override
	public List<Validator<? super TransactionAdditionalTerms>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super TransactionAdditionalTerms, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TransactionAdditionalTerms> validator() {
		return new TransactionAdditionalTermsValidator();
	}

	@Override
	public Validator<? super TransactionAdditionalTerms> typeFormatValidator() {
		return new TransactionAdditionalTermsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TransactionAdditionalTerms, Set<String>> onlyExistsValidator() {
		return new TransactionAdditionalTermsOnlyExistsValidator();
	}
}
