package cdm.product.collateral;

import cdm.product.collateral.EligibleCollateralCriteria;
import cdm.product.collateral.EligibleCollateralSpecificationInstruction;
import cdm.product.collateral.EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder;
import cdm.product.collateral.EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilderImpl;
import cdm.product.collateral.EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionImpl;
import cdm.product.collateral.meta.EligibleCollateralSpecificationInstructionMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="EligibleCollateralSpecificationInstruction", builder=EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilderImpl.class, version="${project.version}")
public interface EligibleCollateralSpecificationInstruction extends RosettaModelObject {

	EligibleCollateralSpecificationInstructionMeta metaData = new EligibleCollateralSpecificationInstructionMeta();

	/*********************** Getter Methods  ***********************/
	EligibleCollateralCriteria getCommon();
	List<? extends EligibleCollateralCriteria> getVariable();

	/*********************** Build Methods  ***********************/
	EligibleCollateralSpecificationInstruction build();
	
	EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder toBuilder();
	
	static EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder builder() {
		return new EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends EligibleCollateralSpecificationInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends EligibleCollateralSpecificationInstruction> getType() {
		return EligibleCollateralSpecificationInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("common"), processor, EligibleCollateralCriteria.class, getCommon());
		processRosetta(path.newSubPath("variable"), processor, EligibleCollateralCriteria.class, getVariable());
	}
	

	/*********************** Builder Interface  ***********************/
	interface EligibleCollateralSpecificationInstructionBuilder extends EligibleCollateralSpecificationInstruction, RosettaModelObjectBuilder {
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder getOrCreateCommon();
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder getCommon();
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder getOrCreateVariable(int _index);
		List<? extends EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder> getVariable();
		EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder setCommon(EligibleCollateralCriteria common);
		EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder addVariable(EligibleCollateralCriteria variable0);
		EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder addVariable(EligibleCollateralCriteria variable1, int _idx);
		EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder addVariable(List<? extends EligibleCollateralCriteria> variable2);
		EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder setVariable(List<? extends EligibleCollateralCriteria> variable3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("common"), processor, EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder.class, getCommon());
			processRosetta(path.newSubPath("variable"), processor, EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder.class, getVariable());
		}
		

		EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of EligibleCollateralSpecificationInstruction  ***********************/
	class EligibleCollateralSpecificationInstructionImpl implements EligibleCollateralSpecificationInstruction {
		private final EligibleCollateralCriteria common;
		private final List<? extends EligibleCollateralCriteria> variable;
		
		protected EligibleCollateralSpecificationInstructionImpl(EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder builder) {
			this.common = ofNullable(builder.getCommon()).map(f->f.build()).orElse(null);
			this.variable = ofNullable(builder.getVariable()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("common")
		public EligibleCollateralCriteria getCommon() {
			return common;
		}
		
		@Override
		@RosettaAttribute("variable")
		public List<? extends EligibleCollateralCriteria> getVariable() {
			return variable;
		}
		
		@Override
		public EligibleCollateralSpecificationInstruction build() {
			return this;
		}
		
		@Override
		public EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder toBuilder() {
			EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder builder) {
			ofNullable(getCommon()).ifPresent(builder::setCommon);
			ofNullable(getVariable()).ifPresent(builder::setVariable);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EligibleCollateralSpecificationInstruction _that = getType().cast(o);
		
			if (!Objects.equals(common, _that.getCommon())) return false;
			if (!ListEquals.listEquals(variable, _that.getVariable())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (common != null ? common.hashCode() : 0);
			_result = 31 * _result + (variable != null ? variable.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EligibleCollateralSpecificationInstruction {" +
				"common=" + this.common + ", " +
				"variable=" + this.variable +
			'}';
		}
	}

	/*********************** Builder Implementation of EligibleCollateralSpecificationInstruction  ***********************/
	class EligibleCollateralSpecificationInstructionBuilderImpl implements EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder {
	
		protected EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder common;
		protected List<EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder> variable = new ArrayList<>();
	
		public EligibleCollateralSpecificationInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("common")
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder getCommon() {
			return common;
		}
		
		@Override
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder getOrCreateCommon() {
			EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder result;
			if (common!=null) {
				result = common;
			}
			else {
				result = common = EligibleCollateralCriteria.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("variable")
		public List<? extends EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder> getVariable() {
			return variable;
		}
		
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder getOrCreateVariable(int _index) {
		
			if (variable==null) {
				this.variable = new ArrayList<>();
			}
			EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder result;
			return getIndex(variable, _index, () -> {
						EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder newVariable = EligibleCollateralCriteria.builder();
						return newVariable;
					});
		}
		
	
		@Override
		@RosettaAttribute("common")
		public EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder setCommon(EligibleCollateralCriteria common) {
			this.common = common==null?null:common.toBuilder();
			return this;
		}
		@Override
		public EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder addVariable(EligibleCollateralCriteria variable) {
			if (variable!=null) this.variable.add(variable.toBuilder());
			return this;
		}
		
		@Override
		public EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder addVariable(EligibleCollateralCriteria variable, int _idx) {
			getIndex(this.variable, _idx, () -> variable.toBuilder());
			return this;
		}
		@Override 
		public EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder addVariable(List<? extends EligibleCollateralCriteria> variables) {
			if (variables != null) {
				for (EligibleCollateralCriteria toAdd : variables) {
					this.variable.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("variable")
		public EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder setVariable(List<? extends EligibleCollateralCriteria> variables) {
			if (variables == null)  {
				this.variable = new ArrayList<>();
			}
			else {
				this.variable = variables.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public EligibleCollateralSpecificationInstruction build() {
			return new EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionImpl(this);
		}
		
		@Override
		public EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder prune() {
			if (common!=null && !common.prune().hasData()) common = null;
			variable = variable.stream().filter(b->b!=null).<EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCommon()!=null && getCommon().hasData()) return true;
			if (getVariable()!=null && getVariable().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder o = (EligibleCollateralSpecificationInstruction.EligibleCollateralSpecificationInstructionBuilder) other;
			
			merger.mergeRosetta(getCommon(), o.getCommon(), this::setCommon);
			merger.mergeRosetta(getVariable(), o.getVariable(), this::getOrCreateVariable);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EligibleCollateralSpecificationInstruction _that = getType().cast(o);
		
			if (!Objects.equals(common, _that.getCommon())) return false;
			if (!ListEquals.listEquals(variable, _that.getVariable())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (common != null ? common.hashCode() : 0);
			_result = 31 * _result + (variable != null ? variable.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EligibleCollateralSpecificationInstructionBuilder {" +
				"common=" + this.common + ", " +
				"variable=" + this.variable +
			'}';
		}
	}
}
