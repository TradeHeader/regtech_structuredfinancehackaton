package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.ShapingProvision;
import cdm.product.common.settlement.validation.ShapingProvisionTypeFormatValidator;
import cdm.product.common.settlement.validation.ShapingProvisionValidator;
import cdm.product.common.settlement.validation.exists.ShapingProvisionOnlyExistsValidator;
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
@RosettaMeta(model=ShapingProvision.class)
public class ShapingProvisionMeta implements RosettaMetaData<ShapingProvision> {

	@Override
	public List<Validator<? super ShapingProvision>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ShapingProvision, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ShapingProvision> validator() {
		return new ShapingProvisionValidator();
	}

	@Override
	public Validator<? super ShapingProvision> typeFormatValidator() {
		return new ShapingProvisionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ShapingProvision, Set<String>> onlyExistsValidator() {
		return new ShapingProvisionOnlyExistsValidator();
	}
}
