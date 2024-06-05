package cdm.event.position.meta;

import cdm.event.position.Inventory;
import cdm.event.position.validation.InventoryTypeFormatValidator;
import cdm.event.position.validation.InventoryValidator;
import cdm.event.position.validation.exists.InventoryOnlyExistsValidator;
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
@RosettaMeta(model=Inventory.class)
public class InventoryMeta implements RosettaMetaData<Inventory> {

	@Override
	public List<Validator<? super Inventory>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Inventory, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Inventory> validator() {
		return new InventoryValidator();
	}

	@Override
	public Validator<? super Inventory> typeFormatValidator() {
		return new InventoryTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Inventory, Set<String>> onlyExistsValidator() {
		return new InventoryOnlyExistsValidator();
	}
}
