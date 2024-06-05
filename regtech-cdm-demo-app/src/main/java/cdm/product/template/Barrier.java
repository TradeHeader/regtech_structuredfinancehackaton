package cdm.product.template;

import cdm.observable.event.TriggerEvent;
import cdm.product.template.Barrier;
import cdm.product.template.Barrier.BarrierBuilder;
import cdm.product.template.Barrier.BarrierBuilderImpl;
import cdm.product.template.Barrier.BarrierImpl;
import cdm.product.template.meta.BarrierMeta;
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
 * As per ISDA 2002 Definitions.
 * @version ${project.version}
 */
@RosettaDataType(value="Barrier", builder=Barrier.BarrierBuilderImpl.class, version="${project.version}")
public interface Barrier extends RosettaModelObject {

	BarrierMeta metaData = new BarrierMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A trigger level approached from beneath.
	 */
	TriggerEvent getBarrierCap();
	/**
	 * A trigger level approached from above.
	 */
	TriggerEvent getBarrierFloor();

	/*********************** Build Methods  ***********************/
	Barrier build();
	
	Barrier.BarrierBuilder toBuilder();
	
	static Barrier.BarrierBuilder builder() {
		return new Barrier.BarrierBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Barrier> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Barrier> getType() {
		return Barrier.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("barrierCap"), processor, TriggerEvent.class, getBarrierCap());
		processRosetta(path.newSubPath("barrierFloor"), processor, TriggerEvent.class, getBarrierFloor());
	}
	

	/*********************** Builder Interface  ***********************/
	interface BarrierBuilder extends Barrier, RosettaModelObjectBuilder {
		TriggerEvent.TriggerEventBuilder getOrCreateBarrierCap();
		TriggerEvent.TriggerEventBuilder getBarrierCap();
		TriggerEvent.TriggerEventBuilder getOrCreateBarrierFloor();
		TriggerEvent.TriggerEventBuilder getBarrierFloor();
		Barrier.BarrierBuilder setBarrierCap(TriggerEvent barrierCap);
		Barrier.BarrierBuilder setBarrierFloor(TriggerEvent barrierFloor);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("barrierCap"), processor, TriggerEvent.TriggerEventBuilder.class, getBarrierCap());
			processRosetta(path.newSubPath("barrierFloor"), processor, TriggerEvent.TriggerEventBuilder.class, getBarrierFloor());
		}
		

		Barrier.BarrierBuilder prune();
	}

	/*********************** Immutable Implementation of Barrier  ***********************/
	class BarrierImpl implements Barrier {
		private final TriggerEvent barrierCap;
		private final TriggerEvent barrierFloor;
		
		protected BarrierImpl(Barrier.BarrierBuilder builder) {
			this.barrierCap = ofNullable(builder.getBarrierCap()).map(f->f.build()).orElse(null);
			this.barrierFloor = ofNullable(builder.getBarrierFloor()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("barrierCap")
		public TriggerEvent getBarrierCap() {
			return barrierCap;
		}
		
		@Override
		@RosettaAttribute("barrierFloor")
		public TriggerEvent getBarrierFloor() {
			return barrierFloor;
		}
		
		@Override
		public Barrier build() {
			return this;
		}
		
		@Override
		public Barrier.BarrierBuilder toBuilder() {
			Barrier.BarrierBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Barrier.BarrierBuilder builder) {
			ofNullable(getBarrierCap()).ifPresent(builder::setBarrierCap);
			ofNullable(getBarrierFloor()).ifPresent(builder::setBarrierFloor);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Barrier _that = getType().cast(o);
		
			if (!Objects.equals(barrierCap, _that.getBarrierCap())) return false;
			if (!Objects.equals(barrierFloor, _that.getBarrierFloor())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (barrierCap != null ? barrierCap.hashCode() : 0);
			_result = 31 * _result + (barrierFloor != null ? barrierFloor.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Barrier {" +
				"barrierCap=" + this.barrierCap + ", " +
				"barrierFloor=" + this.barrierFloor +
			'}';
		}
	}

	/*********************** Builder Implementation of Barrier  ***********************/
	class BarrierBuilderImpl implements Barrier.BarrierBuilder {
	
		protected TriggerEvent.TriggerEventBuilder barrierCap;
		protected TriggerEvent.TriggerEventBuilder barrierFloor;
	
		public BarrierBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("barrierCap")
		public TriggerEvent.TriggerEventBuilder getBarrierCap() {
			return barrierCap;
		}
		
		@Override
		public TriggerEvent.TriggerEventBuilder getOrCreateBarrierCap() {
			TriggerEvent.TriggerEventBuilder result;
			if (barrierCap!=null) {
				result = barrierCap;
			}
			else {
				result = barrierCap = TriggerEvent.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("barrierFloor")
		public TriggerEvent.TriggerEventBuilder getBarrierFloor() {
			return barrierFloor;
		}
		
		@Override
		public TriggerEvent.TriggerEventBuilder getOrCreateBarrierFloor() {
			TriggerEvent.TriggerEventBuilder result;
			if (barrierFloor!=null) {
				result = barrierFloor;
			}
			else {
				result = barrierFloor = TriggerEvent.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("barrierCap")
		public Barrier.BarrierBuilder setBarrierCap(TriggerEvent barrierCap) {
			this.barrierCap = barrierCap==null?null:barrierCap.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("barrierFloor")
		public Barrier.BarrierBuilder setBarrierFloor(TriggerEvent barrierFloor) {
			this.barrierFloor = barrierFloor==null?null:barrierFloor.toBuilder();
			return this;
		}
		
		@Override
		public Barrier build() {
			return new Barrier.BarrierImpl(this);
		}
		
		@Override
		public Barrier.BarrierBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Barrier.BarrierBuilder prune() {
			if (barrierCap!=null && !barrierCap.prune().hasData()) barrierCap = null;
			if (barrierFloor!=null && !barrierFloor.prune().hasData()) barrierFloor = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getBarrierCap()!=null && getBarrierCap().hasData()) return true;
			if (getBarrierFloor()!=null && getBarrierFloor().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Barrier.BarrierBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Barrier.BarrierBuilder o = (Barrier.BarrierBuilder) other;
			
			merger.mergeRosetta(getBarrierCap(), o.getBarrierCap(), this::setBarrierCap);
			merger.mergeRosetta(getBarrierFloor(), o.getBarrierFloor(), this::setBarrierFloor);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Barrier _that = getType().cast(o);
		
			if (!Objects.equals(barrierCap, _that.getBarrierCap())) return false;
			if (!Objects.equals(barrierFloor, _that.getBarrierFloor())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (barrierCap != null ? barrierCap.hashCode() : 0);
			_result = 31 * _result + (barrierFloor != null ? barrierFloor.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BarrierBuilder {" +
				"barrierCap=" + this.barrierCap + ", " +
				"barrierFloor=" + this.barrierFloor +
			'}';
		}
	}
}
