package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.Loan;
import cdm.base.staticdata.asset.common.validation.LoanTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.LoanValidator;
import cdm.base.staticdata.asset.common.validation.exists.LoanOnlyExistsValidator;
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
@RosettaMeta(model=Loan.class)
public class LoanMeta implements RosettaMetaData<Loan> {

	@Override
	public List<Validator<? super Loan>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Loan, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Loan> validator() {
		return new LoanValidator();
	}

	@Override
	public Validator<? super Loan> typeFormatValidator() {
		return new LoanTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Loan, Set<String>> onlyExistsValidator() {
		return new LoanOnlyExistsValidator();
	}
}
