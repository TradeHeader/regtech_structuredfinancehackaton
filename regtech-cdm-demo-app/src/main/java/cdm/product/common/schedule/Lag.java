package cdm.product.common.schedule;

import cdm.base.datetime.Offset;
import cdm.product.common.schedule.Lag;
import cdm.product.common.schedule.Lag.LagBuilder;
import cdm.product.common.schedule.Lag.LagBuilderImpl;
import cdm.product.common.schedule.Lag.LagImpl;
import cdm.product.common.schedule.meta.LagMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * The pricing period per calculation period if the pricing days do not wholly fall within the respective calculation period.
 * @version ${project.version}
 */
@RosettaDataType(value="Lag", builder=Lag.LagBuilderImpl.class, version="${project.version}")
public interface Lag extends RosettaModelObject {

	LagMeta metaData = new LagMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Defines the offset of the series of pricing dates relative to the calculation period.
	 */
	Offset getLagDuration();
	/**
	 * Defines the offset of the series of pricing dates relative to the calculation period.
	 */
	Offset getFirstObservationDateOffset();

	/*********************** Build Methods  ***********************/
	Lag build();
	
	Lag.LagBuilder toBuilder();
	
	static Lag.LagBuilder builder() {
		return new Lag.LagBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Lag> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Lag> getType() {
		return Lag.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("lagDuration"), processor, Offset.class, getLagDuration());
		processRosetta(path.newSubPath("firstObservationDateOffset"), processor, Offset.class, getFirstObservationDateOffset());
	}
	

	/*********************** Builder Interface  ***********************/
	interface LagBuilder extends Lag, RosettaModelObjectBuilder {
		Offset.OffsetBuilder getOrCreateLagDuration();
		Offset.OffsetBuilder getLagDuration();
		Offset.OffsetBuilder getOrCreateFirstObservationDateOffset();
		Offset.OffsetBuilder getFirstObservationDateOffset();
		Lag.LagBuilder setLagDuration(Offset lagDuration);
		Lag.LagBuilder setFirstObservationDateOffset(Offset firstObservationDateOffset);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("lagDuration"), processor, Offset.OffsetBuilder.class, getLagDuration());
			processRosetta(path.newSubPath("firstObservationDateOffset"), processor, Offset.OffsetBuilder.class, getFirstObservationDateOffset());
		}
		

		Lag.LagBuilder prune();
	}

	/*********************** Immutable Implementation of Lag  ***********************/
	class LagImpl implements Lag {
		private final Offset lagDuration;
		private final Offset firstObservationDateOffset;
		
		protected LagImpl(Lag.LagBuilder builder) {
			this.lagDuration = ofNullable(builder.getLagDuration()).map(f->f.build()).orElse(null);
			this.firstObservationDateOffset = ofNullable(builder.getFirstObservationDateOffset()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("lagDuration")
		public Offset getLagDuration() {
			return lagDuration;
		}
		
		@Override
		@RosettaAttribute("firstObservationDateOffset")
		public Offset getFirstObservationDateOffset() {
			return firstObservationDateOffset;
		}
		
		@Override
		public Lag build() {
			return this;
		}
		
		@Override
		public Lag.LagBuilder toBuilder() {
			Lag.LagBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Lag.LagBuilder builder) {
			ofNullable(getLagDuration()).ifPresent(builder::setLagDuration);
			ofNullable(getFirstObservationDateOffset()).ifPresent(builder::setFirstObservationDateOffset);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Lag _that = getType().cast(o);
		
			if (!Objects.equals(lagDuration, _that.getLagDuration())) return false;
			if (!Objects.equals(firstObservationDateOffset, _that.getFirstObservationDateOffset())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (lagDuration != null ? lagDuration.hashCode() : 0);
			_result = 31 * _result + (firstObservationDateOffset != null ? firstObservationDateOffset.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Lag {" +
				"lagDuration=" + this.lagDuration + ", " +
				"firstObservationDateOffset=" + this.firstObservationDateOffset +
			'}';
		}
	}

	/*********************** Builder Implementation of Lag  ***********************/
	class LagBuilderImpl implements Lag.LagBuilder {
	
		protected Offset.OffsetBuilder lagDuration;
		protected Offset.OffsetBuilder firstObservationDateOffset;
	
		public LagBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("lagDuration")
		public Offset.OffsetBuilder getLagDuration() {
			return lagDuration;
		}
		
		@Override
		public Offset.OffsetBuilder getOrCreateLagDuration() {
			Offset.OffsetBuilder result;
			if (lagDuration!=null) {
				result = lagDuration;
			}
			else {
				result = lagDuration = Offset.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("firstObservationDateOffset")
		public Offset.OffsetBuilder getFirstObservationDateOffset() {
			return firstObservationDateOffset;
		}
		
		@Override
		public Offset.OffsetBuilder getOrCreateFirstObservationDateOffset() {
			Offset.OffsetBuilder result;
			if (firstObservationDateOffset!=null) {
				result = firstObservationDateOffset;
			}
			else {
				result = firstObservationDateOffset = Offset.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("lagDuration")
		public Lag.LagBuilder setLagDuration(Offset lagDuration) {
			this.lagDuration = lagDuration==null?null:lagDuration.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("firstObservationDateOffset")
		public Lag.LagBuilder setFirstObservationDateOffset(Offset firstObservationDateOffset) {
			this.firstObservationDateOffset = firstObservationDateOffset==null?null:firstObservationDateOffset.toBuilder();
			return this;
		}
		
		@Override
		public Lag build() {
			return new Lag.LagImpl(this);
		}
		
		@Override
		public Lag.LagBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Lag.LagBuilder prune() {
			if (lagDuration!=null && !lagDuration.prune().hasData()) lagDuration = null;
			if (firstObservationDateOffset!=null && !firstObservationDateOffset.prune().hasData()) firstObservationDateOffset = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getLagDuration()!=null && getLagDuration().hasData()) return true;
			if (getFirstObservationDateOffset()!=null && getFirstObservationDateOffset().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Lag.LagBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Lag.LagBuilder o = (Lag.LagBuilder) other;
			
			merger.mergeRosetta(getLagDuration(), o.getLagDuration(), this::setLagDuration);
			merger.mergeRosetta(getFirstObservationDateOffset(), o.getFirstObservationDateOffset(), this::setFirstObservationDateOffset);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Lag _that = getType().cast(o);
		
			if (!Objects.equals(lagDuration, _that.getLagDuration())) return false;
			if (!Objects.equals(firstObservationDateOffset, _that.getFirstObservationDateOffset())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (lagDuration != null ? lagDuration.hashCode() : 0);
			_result = 31 * _result + (firstObservationDateOffset != null ? firstObservationDateOffset.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LagBuilder {" +
				"lagDuration=" + this.lagDuration + ", " +
				"firstObservationDateOffset=" + this.firstObservationDateOffset +
			'}';
		}
	}
}
