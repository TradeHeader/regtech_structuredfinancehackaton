package cdm.regulation.meta;

import cdm.regulation.Qty;
import cdm.regulation.validation.QtyTypeFormatValidator;
import cdm.regulation.validation.QtyValidator;
import cdm.regulation.validation.exists.QtyOnlyExistsValidator;
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
@RosettaMeta(model=Qty.class)
public class QtyMeta implements RosettaMetaData<Qty> {

	@Override
	public List<Validator<? super Qty>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Qty, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Qty> validator() {
		return new QtyValidator();
	}

	@Override
	public Validator<? super Qty> typeFormatValidator() {
		return new QtyTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Qty, Set<String>> onlyExistsValidator() {
		return new QtyOnlyExistsValidator();
	}
}
