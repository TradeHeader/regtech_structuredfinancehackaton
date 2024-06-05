package cdm.regulation.meta;

import cdm.regulation.Tx;
import cdm.regulation.validation.TxTypeFormatValidator;
import cdm.regulation.validation.TxValidator;
import cdm.regulation.validation.exists.TxOnlyExistsValidator;
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
@RosettaMeta(model=Tx.class)
public class TxMeta implements RosettaMetaData<Tx> {

	@Override
	public List<Validator<? super Tx>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Tx, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Tx> validator() {
		return new TxValidator();
	}

	@Override
	public Validator<? super Tx> typeFormatValidator() {
		return new TxTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Tx, Set<String>> onlyExistsValidator() {
		return new TxOnlyExistsValidator();
	}
}
