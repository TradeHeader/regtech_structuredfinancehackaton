package cdm.event.common;

import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.SplitInstruction;
import cdm.event.common.SplitInstruction.SplitInstructionBuilder;
import cdm.event.common.SplitInstruction.SplitInstructionBuilderImpl;
import cdm.event.common.SplitInstruction.SplitInstructionImpl;
import cdm.event.common.meta.SplitInstructionMeta;
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
 * Specifies instructions for a split, consisting of a breakdown of instructions to be applied to each branch of the split. This instruction can be used to duplicate a trade, as in a clearing scenario, or to split a trade into smaller quantities (in which case each breakdown instruction needs to include a quantity change), as in an allocation.
 * @version ${project.version}
 */
@RosettaDataType(value="SplitInstruction", builder=SplitInstruction.SplitInstructionBuilderImpl.class, version="${project.version}")
public interface SplitInstruction extends RosettaModelObject {

	SplitInstructionMeta metaData = new SplitInstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Each split breakdown specifies the set of primitive instructions to be applied to a single branch of that split. N split breakdowns result in N output trades, which include the original trade. Instructions for how to handle the original trade (e.g. if it must be closed) must be specified in one of the breakdowns.
	 */
	List<? extends PrimitiveInstruction> getBreakdown();

	/*********************** Build Methods  ***********************/
	SplitInstruction build();
	
	SplitInstruction.SplitInstructionBuilder toBuilder();
	
	static SplitInstruction.SplitInstructionBuilder builder() {
		return new SplitInstruction.SplitInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SplitInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SplitInstruction> getType() {
		return SplitInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("breakdown"), processor, PrimitiveInstruction.class, getBreakdown());
	}
	

	/*********************** Builder Interface  ***********************/
	interface SplitInstructionBuilder extends SplitInstruction, RosettaModelObjectBuilder {
		PrimitiveInstruction.PrimitiveInstructionBuilder getOrCreateBreakdown(int _index);
		List<? extends PrimitiveInstruction.PrimitiveInstructionBuilder> getBreakdown();
		SplitInstruction.SplitInstructionBuilder addBreakdown(PrimitiveInstruction breakdown0);
		SplitInstruction.SplitInstructionBuilder addBreakdown(PrimitiveInstruction breakdown1, int _idx);
		SplitInstruction.SplitInstructionBuilder addBreakdown(List<? extends PrimitiveInstruction> breakdown2);
		SplitInstruction.SplitInstructionBuilder setBreakdown(List<? extends PrimitiveInstruction> breakdown3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("breakdown"), processor, PrimitiveInstruction.PrimitiveInstructionBuilder.class, getBreakdown());
		}
		

		SplitInstruction.SplitInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of SplitInstruction  ***********************/
	class SplitInstructionImpl implements SplitInstruction {
		private final List<? extends PrimitiveInstruction> breakdown;
		
		protected SplitInstructionImpl(SplitInstruction.SplitInstructionBuilder builder) {
			this.breakdown = ofNullable(builder.getBreakdown()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("breakdown")
		public List<? extends PrimitiveInstruction> getBreakdown() {
			return breakdown;
		}
		
		@Override
		public SplitInstruction build() {
			return this;
		}
		
		@Override
		public SplitInstruction.SplitInstructionBuilder toBuilder() {
			SplitInstruction.SplitInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SplitInstruction.SplitInstructionBuilder builder) {
			ofNullable(getBreakdown()).ifPresent(builder::setBreakdown);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SplitInstruction _that = getType().cast(o);
		
			if (!ListEquals.listEquals(breakdown, _that.getBreakdown())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (breakdown != null ? breakdown.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SplitInstruction {" +
				"breakdown=" + this.breakdown +
			'}';
		}
	}

	/*********************** Builder Implementation of SplitInstruction  ***********************/
	class SplitInstructionBuilderImpl implements SplitInstruction.SplitInstructionBuilder {
	
		protected List<PrimitiveInstruction.PrimitiveInstructionBuilder> breakdown = new ArrayList<>();
	
		public SplitInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("breakdown")
		public List<? extends PrimitiveInstruction.PrimitiveInstructionBuilder> getBreakdown() {
			return breakdown;
		}
		
		public PrimitiveInstruction.PrimitiveInstructionBuilder getOrCreateBreakdown(int _index) {
		
			if (breakdown==null) {
				this.breakdown = new ArrayList<>();
			}
			PrimitiveInstruction.PrimitiveInstructionBuilder result;
			return getIndex(breakdown, _index, () -> {
						PrimitiveInstruction.PrimitiveInstructionBuilder newBreakdown = PrimitiveInstruction.builder();
						return newBreakdown;
					});
		}
		
	
		@Override
		public SplitInstruction.SplitInstructionBuilder addBreakdown(PrimitiveInstruction breakdown) {
			if (breakdown!=null) this.breakdown.add(breakdown.toBuilder());
			return this;
		}
		
		@Override
		public SplitInstruction.SplitInstructionBuilder addBreakdown(PrimitiveInstruction breakdown, int _idx) {
			getIndex(this.breakdown, _idx, () -> breakdown.toBuilder());
			return this;
		}
		@Override 
		public SplitInstruction.SplitInstructionBuilder addBreakdown(List<? extends PrimitiveInstruction> breakdowns) {
			if (breakdowns != null) {
				for (PrimitiveInstruction toAdd : breakdowns) {
					this.breakdown.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("breakdown")
		public SplitInstruction.SplitInstructionBuilder setBreakdown(List<? extends PrimitiveInstruction> breakdowns) {
			if (breakdowns == null)  {
				this.breakdown = new ArrayList<>();
			}
			else {
				this.breakdown = breakdowns.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public SplitInstruction build() {
			return new SplitInstruction.SplitInstructionImpl(this);
		}
		
		@Override
		public SplitInstruction.SplitInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SplitInstruction.SplitInstructionBuilder prune() {
			breakdown = breakdown.stream().filter(b->b!=null).<PrimitiveInstruction.PrimitiveInstructionBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getBreakdown()!=null && getBreakdown().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SplitInstruction.SplitInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SplitInstruction.SplitInstructionBuilder o = (SplitInstruction.SplitInstructionBuilder) other;
			
			merger.mergeRosetta(getBreakdown(), o.getBreakdown(), this::getOrCreateBreakdown);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SplitInstruction _that = getType().cast(o);
		
			if (!ListEquals.listEquals(breakdown, _that.getBreakdown())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (breakdown != null ? breakdown.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SplitInstructionBuilder {" +
				"breakdown=" + this.breakdown +
			'}';
		}
	}
}
