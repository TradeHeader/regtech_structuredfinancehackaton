package cdm.regulation.meta;

import cdm.regulation.FinInstrmRptgTxRpt;
import cdm.regulation.validation.FinInstrmRptgTxRptTypeFormatValidator;
import cdm.regulation.validation.FinInstrmRptgTxRptValidator;
import cdm.regulation.validation.exists.FinInstrmRptgTxRptOnlyExistsValidator;
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
@RosettaMeta(model=FinInstrmRptgTxRpt.class)
public class FinInstrmRptgTxRptMeta implements RosettaMetaData<FinInstrmRptgTxRpt> {

	@Override
	public List<Validator<? super FinInstrmRptgTxRpt>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FinInstrmRptgTxRpt, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FinInstrmRptgTxRpt> validator() {
		return new FinInstrmRptgTxRptValidator();
	}

	@Override
	public Validator<? super FinInstrmRptgTxRpt> typeFormatValidator() {
		return new FinInstrmRptgTxRptTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FinInstrmRptgTxRpt, Set<String>> onlyExistsValidator() {
		return new FinInstrmRptgTxRptOnlyExistsValidator();
	}
}
